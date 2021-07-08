package model.io;

import model.logic.Customer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class LoadCustomers {
    /**
     * Loads a customer via filename.
     * @param filename filename with correct ending.
     * @return customer from file or null if errors has occurred.
     */
    public static Customer loadCustomerFromDisk(String filename){
        try(var reader = new ObjectInputStream(new FileInputStream("Customers/" + filename))){
            return (Customer) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Customer> loadAllCustomerFromDisk(){
        LinkedList<Customer> l = new LinkedList<>();
        File homeDir = new File("Customers/");
        if (homeDir.exists()) {
            for (File f : homeDir.listFiles()){
                if (f.getName().endsWith(".ser")) {
                    l.add(loadCustomerFromDisk(f.getName()));
                }
            }
        }
        return l;


    }
}
