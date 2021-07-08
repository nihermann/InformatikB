package model.io;

import java.io.File;

public class RemoveCustomers {
    /**
     * deletes a serialized Customer from disk.
     * @param customerId id of the customer.
     */
    public static void removeCustomerFromDisk(String customerId){
        // IOException will never be thrown.
        File f = new File("Customers/" + customerId + ".ser");
        if (f.exists()){
            f.delete();
        }
    }

    public static void removeCustomerFromDiskConcurrent(String customerId){
        new Thread(()-> removeCustomerFromDisk(customerId)).start();
    }
}
