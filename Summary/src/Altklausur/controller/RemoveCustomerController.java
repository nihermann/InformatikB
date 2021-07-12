package Altklausur.controller;

import Altklausur.model.io.RemoveCustomerVisitor;
import Altklausur.model.logic.Customer;
import Altklausur.model.logic.CustomerSystem;
import Altklausur.view.AddCustomerPanel;
import Altklausur.view.RemoveCustomerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveCustomerController implements ActionListener {

    private CustomerSystem model;
    private RemoveCustomerPanel view;

    public RemoveCustomerController(CustomerSystem model, RemoveCustomerPanel view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            System.out.println("Visitor sucht nach dieser id: " + this.view.getCustomerIdToRemove().getText());
            RemoveCustomerVisitor removeVisitor = new RemoveCustomerVisitor(this.view.getCustomerIdToRemove().getText(), this.model);
            model.accept(removeVisitor);
        }catch (IllegalStateException exception){
            JOptionPane.showMessageDialog(null, "The given name did not mean the registering criteria");
        }
    }
}
