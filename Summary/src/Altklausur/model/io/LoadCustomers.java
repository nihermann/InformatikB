package Altklausur.model.io;

import Altklausur.model.logic.Customer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class LoadCustomers {

    public static Customer loadCustomerFromDisk(String filename){
        Customer customer = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Customers/" + filename))){
            customer = (Customer) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public static List<Customer> loadCustomerFromDiskAll(){
        File customers = new File("Customers/");
        File[] allCustomers = customers.listFiles();

        List<Customer> customerList = new LinkedList<Customer>();
        for(File customer : allCustomers){
            customerList.add(loadCustomerFromDisk(customer.getName()));
        }

        return customerList;
    }

}
