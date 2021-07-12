package Altklausur.model.logic;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer implements Serializable {
    private static long serialVersionUID = 5L;

    private String name;
    private String customerID;

    private final String req = "[1-9]{4}-[A-Z]{4}";

    public Customer(String name, String customerID){
        this.name = name;
        Pattern reqPattern = Pattern.compile(req);
        Matcher matcher = reqPattern.matcher(customerID);
        if (!matcher.matches()){
            throw new IllegalStateException("The customer ID was not conform.");
        }
        this.customerID = customerID;
    }


    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerID, customer.customerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID) +5;
    }

    public String getName() {
        return name;
    }


    public String getCustomerID() {
        return customerID;
    }


}
