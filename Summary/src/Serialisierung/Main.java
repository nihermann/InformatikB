package Serialisierung;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Serialisierung/fusballer.ser"))){
            Fußballspieler fußballspieler = new Fußballspieler(18, 10, "Lutz");
            oos.writeObject(fußballspieler);

            // flush so that every thing is written into the ser file
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Serialisierung/fusballer.ser"))) {
            Fußballspieler fußballspieler = (Fußballspieler) ois.readObject();
            System.out.println(fußballspieler);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
