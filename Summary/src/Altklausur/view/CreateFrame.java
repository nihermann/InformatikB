package Altklausur.view;

import Altklausur.model.logic.CustomerSystem;

import javax.swing.*;

public class CreateFrame {

    public void createGUI(CustomerSystem model){
        JFrame frame = new JFrame("Customer System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SystemPanel systemPanel = new SystemPanel(model);
        frame.setContentPane(systemPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
