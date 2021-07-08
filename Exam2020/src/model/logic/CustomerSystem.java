package model.logic;

import model.visitorInterfaces.Visitable;
import model.visitorInterfaces.Visitor;
import model.io.LoadCustomers;
import model.io.RemoveCustomers;
import model.io.StoreCustomers;

import java.util.List;
import java.util.Observable;

public class CustomerSystem extends Observable implements Visitable<Customer> {
    private List<Customer> customers;

    public CustomerSystem(){
        customers = LoadCustomers.loadAllCustomerFromDisk();
    }

    public synchronized boolean addCustomer(Customer toAdd){
        if(customers.contains(toAdd)) return false;

        StoreCustomers.storeCustomerOnDiskConcurrent(toAdd);
        customers.add(toAdd);

        this.setChanged();
        this.notifyObservers();
        return true;
    }

    /**
     * removes a customer permanently.
     * @param toRemove to remove.
     */
    public synchronized void removeCustomer(Customer toRemove){
            customers.remove(toRemove);
            RemoveCustomers.removeCustomerFromDiskConcurrent(toRemove.getCustomerId());
            this.setChanged();
            this.notifyObservers();
    }

    public List<Customer> getCustomers(){
        return customers;
    }

    public void accept(Visitor<Customer> v) {
        for(Customer c: customers){
            if(!v.visit(c)) break;
        }
    }
}
