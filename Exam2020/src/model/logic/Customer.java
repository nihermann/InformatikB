package model.logic;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {
    private final static long serialVersionUID = 34;

    String name;
    String customerId;

    public String getName(){
        return name;
    }

    public String getCustomerId(){
        return customerId;
    }

    /**
     * Creates a new customer.
     * @param name name of the customer
     * @param customerId unique id which has the format [1-9][1-9][1-9][1-9]-[A-Z][A-Z][A-Z][A-Z].
     */
    public Customer(String name, String customerId){
        this.name = name;
        this.customerId = customerId;

        // check id
        String regex = "[1-9][1-9][1-9][1-9]-[A-Z][A-Z][A-Z][A-Z]";
        if (!customerId.matches(regex)){
            throw new IllegalArgumentException("customer Id must be of format: " + regex);
        }
    }

    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId) + 34;
    }
}
