package Altklausur.controller;

import Altklausur.model.logic.Customer;
import Altklausur.model.logic.CustomerSystem;
import Altklausur.view.AddCustomerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerController implements ActionListener {

    private CustomerSystem model;
    private AddCustomerPanel view;

    public AddCustomerController(CustomerSystem model, AddCustomerPanel view){
        this.model = model;
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            Customer customer = new Customer(view.getNameInput().getText(), view.getCustomerIdInput().getText());
            if(!model.addCustomer(customer)){
                JOptionPane.showMessageDialog(null, "Customer already exists");
            }
        }catch (IllegalStateException exception){
            JOptionPane.showMessageDialog(null, "The given name did not mean the registering criteria");
        }

    }
}
