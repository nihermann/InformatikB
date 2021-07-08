package view;

import model.logic.CustomerSystem;

import javax.swing.*;

public class CreateFrame {
    public static void createGUI(CustomerSystem model){
        JFrame frame = new JFrame("CustomerSystem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SystemPanel systemPanel = new SystemPanel(model);
        frame.add(systemPanel);
        frame.setVisible(true);
    }
}
