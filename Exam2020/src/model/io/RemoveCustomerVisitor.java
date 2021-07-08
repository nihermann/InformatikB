package model.io;

import model.logic.Customer;
import model.logic.CustomerSystem;
import model.visitorInterfaces.Visitor;

public class RemoveCustomerVisitor implements Visitor<Customer> {
    private String customerToSearch;
    private CustomerSystem systemToSearch;

    /**
     * Constructs new Visitor which will search for an valid customer id. If he finds it, the customer will be deleted
     * in the systemToSearch. Afterwards visiting will be terminated.
     * @param customerIdToSearch valid customer id which will used for comparison and deletion.
     * @param systemToSearch the system in which the customer will be deleted.
     */
    public RemoveCustomerVisitor(String customerIdToSearch, CustomerSystem systemToSearch){
        String regex = "[1-9][1-9][1-9][1-9]-[A-Z][A-Z][A-Z][A-Z]";
        if (!customerIdToSearch.matches(regex)){
            throw new IllegalArgumentException("customer Id must be of format: " + regex);
        }
        this.customerToSearch = customerIdToSearch;
        this.systemToSearch = systemToSearch;

    }

    @Override
    public boolean visit(Customer o) {
        if(o.getCustomerId().equals(customerToSearch)){
            systemToSearch.removeCustomer(o);
            return false;
        }
        return true;
    }
}
