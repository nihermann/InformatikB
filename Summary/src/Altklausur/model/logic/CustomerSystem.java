package Altklausur.model.logic;

import Altklausur.model.io.LoadCustomers;
import Altklausur.model.io.RemoveCustomers;
import Altklausur.model.io.StoreCustomers;
import Altklausur.model.logic.visitorInterfaces.Visitable;
import Altklausur.model.logic.visitorInterfaces.Visitor;


import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class CustomerSystem extends Observable implements Visitable<Customer> {



    List<Customer> customers;

    public CustomerSystem(){
        this.customers = LoadCustomers.loadCustomerFromDiskAll();
    }

    public boolean addCustomer(Customer toAdd){
        if(customers.contains(toAdd)){
            return false;
        }
        customers.add(toAdd);
        System.out.println("Customer was added?");
        StoreCustomers.storeCustomerOnDiskConcurrent(toAdd);
        this.setChanged();
        this.notifyObservers();
        return true;
    }

    public void removeCustomer(Customer toRemove){
        customers.remove(toRemove);
        RemoveCustomers.removeCustomerFromDiskConcurrent(toRemove.getCustomerID()+".ser");
        System.out.println("Im Remove Customer vom System");
        this.setChanged();
        this.notifyObservers();
    }
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public void accept(Visitor<Customer> v) {

        boolean goOnVisiting = true;
        System.out.println("Bin im accept");

        for (int i = 0; i < customers.size(); i++) {
            if(!goOnVisiting){
                break;
            }
            System.out.println("Bin im for der accept");
            goOnVisiting = v.visit(customers.get(i));
        }
    }
}
