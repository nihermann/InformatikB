package Altklausur.model.io;

import Altklausur.model.logic.Customer;

import java.io.*;

public class StoreCustomers {

    public static void storeCustomerOnDisk(Customer toStore){
        File customersDir = new File("Customers");
        if(!customersDir.exists()){
            customersDir.mkdir();
        }

        File currentCustomer = new File("Customers/"+toStore.getCustomerID()+".ser");
        try {
            currentCustomer.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentCustomer))){
            oos.writeObject(toStore);
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void storeCustomerOnDiskConcurrent(Customer toStore){
        Thread toStoreSaver = new Thread(){
            @Override
            public void run(){
                storeCustomerOnDisk(toStore);
            }
        };
        toStoreSaver.start();
    }

}
