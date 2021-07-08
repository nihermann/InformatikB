package Serialization;

import java.io.*;

public class Serializer {
    public static void main(String[] args) {
        Persistent p = new Persistent(2, "aölsdk");

        try(var writer = new ObjectOutputStream(new FileOutputStream("FILENAME.ser"))){
            writer.writeObject(p);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(var reader = new ObjectInputStream(new FileInputStream("FILENAME.ser"))){
            Persistent fromFile = (Persistent) reader.readObject();
            System.out.println(fromFile);
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
