package model.io;

import model.logic.Customer;

import java.io.*;

public class StoreCustomers {
    /**
     * Serializes a customer to Customers/.
     * @param toStore customer to store.
     */
    public static void storeCustomerOnDisk(Customer toStore){
        File dest = new File("Customers/"+toStore.getCustomerId()+".ser");
//        if(dest.exists()) dest.mkdir();

        try(var writer = new ObjectOutputStream(new FileOutputStream(dest))){
            writer.writeObject(toStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void storeCustomerOnDiskConcurrent(Customer toStore){
        new Thread(()->{
            storeCustomerOnDisk(toStore);
        }).start();
    }
}
