package Altklausur.model.io;

import Altklausur.model.logic.Customer;
import Altklausur.model.logic.CustomerSystem;
import Altklausur.model.logic.visitorInterfaces.Visitor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveCustomerVisitor implements Visitor<Customer> {

    CustomerSystem customerSystem;
    String customerToSearch;
    private final String req = "[1-9]{4}-[A-Z]{4}";


    public RemoveCustomerVisitor(String customerIdToSearch, CustomerSystem systemToSearch){
        Pattern reqPattern = Pattern.compile(req);
        Matcher matcher = reqPattern.matcher(customerIdToSearch);

        if (!matcher.matches()){
            throw new IllegalStateException("The customer ID was not conform.");
        }

        this.customerToSearch = customerIdToSearch;
        this.customerSystem = systemToSearch;
    }
    @Override
    public boolean visit(Customer o) {
        System.out.println("Im visit und ich suche nach der id: " + customerToSearch);
        if(o.getCustomerID().equals(customerToSearch)){
            customerSystem.removeCustomer(o);
            return false;
        }
        return true;
    }
}
