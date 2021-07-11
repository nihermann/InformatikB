package Altklausur.model.io;

import java.io.File;

public class RemoveCustomers {

    public static void removeCustomerFromDisk(String customerId){
        File currentCustomer = new File("Customers/"+customerId);
        currentCustomer.delete();
    }

    public static void removeCustomerFromDiskConcurrent(String customerId){
        Thread removeCustomer = new Thread(){
             @Override
            public void run(){
                 removeCustomerFromDisk(customerId);
             }
        };
        System.out.println("File was deleted?");
        removeCustomer.start();
    }

}
