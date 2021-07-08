package controller;

import model.io.RemoveCustomerVisitor;
import model.logic.CustomerSystem;
import view.RemoveCustomerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveCustomerController implements ActionListener {

    private final RemoveCustomerPanel view;
    private final CustomerSystem model;

    public RemoveCustomerController(CustomerSystem model, RemoveCustomerPanel view){
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
        String id = view.getCustomerIdToRemove().getText();
        try {
            RemoveCustomerVisitor v = new RemoveCustomerVisitor(id, model);
            model.accept(v); // if v is not created, no need to accept it.
        } catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(view, "Failed to delete customer because: " + e.getMessage());
        }

    }
}
