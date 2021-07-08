package aufg5;

import javax.swing.*;

public class Light {
    public static void main(String[] ars){
        try {
            View v = new View(new Model());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
