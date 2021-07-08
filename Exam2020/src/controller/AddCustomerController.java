package controller;

import model.logic.Customer;
import model.logic.CustomerSystem;
import view.AddCustomerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerController implements ActionListener {
    private final AddCustomerPanel view;
    private final CustomerSystem model;

    public AddCustomerController(CustomerSystem model, AddCustomerPanel view){
        this.model = model;
        this.view = view;
    }


    /**
     * Invoked when an action occurs.
     *
     * @param arg0 the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String name = view.getNameInput().getText();
        String id = view.getCustomerIdInput().getText();
        try {
            if (!model.addCustomer(new Customer(name, id))) {
                JOptionPane.showMessageDialog(view, "Customer already exists");
            }
        } catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(view, "Failed to create a new customer because: " + e.getMessage());
        }
    }
}
