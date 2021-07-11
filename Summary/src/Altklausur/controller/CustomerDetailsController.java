package Altklausur.controller;

import Altklausur.model.logic.Customer;
import Altklausur.view.CustomerDetailsPanel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CustomerDetailsController implements ListSelectionListener {

    private CustomerDetailsPanel view;

    public CustomerDetailsController(CustomerDetailsPanel view){
        this.view = view;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Customer customer;
        if(this.view.getCustomersInTheSystem().getModel().getSize() >=1){
            customer = this.view.getCustomersInTheSystem().getModel().getElementAt(0);
        }
        customer = this.view.getCustomersInTheSystem().getSelectedValue();

        this.view.getCustomerDetails().setText("Name: " + customer.getName() +"\n" +
                "Customer-ID" + customer.getCustomerID());
    }
}