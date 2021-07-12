package Altklausur.view;

import Altklausur.controller.CustomerDetailsController;
import Altklausur.model.logic.Customer;
import Altklausur.model.logic.CustomerSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class CustomerDetailsPanel extends JPanel implements Observer {

    private static long serialVersionUID = 5L;

    private JScrollPane customersInTheSystemScrollPane;
    private JList<Customer> customersInTheSystem;
    private JTextArea customerDetails;
    private CustomerSystem model;

    public CustomerDetailsPanel(CustomerSystem model) {
        this.model = model;
        this.customersInTheSystemScrollPane = new JScrollPane();
        this.customersInTheSystem = new JList<Customer>(model.getCustomers().toArray(Customer[]::new));
        this.customersInTheSystem.addListSelectionListener(new CustomerDetailsController(this));
        this.customersInTheSystemScrollPane.add(this.customersInTheSystem);
        this.customerDetails = new JTextArea();
        this.customerDetails.setPreferredSize(new Dimension(155,105));
        this.customerDetails.setEnabled(false);
        super.setBorder(BorderFactory.createLineBorder(Color.black));
        this.model.addObserver(this);
        this.add(customersInTheSystemScrollPane);
        this.add(customersInTheSystem);
        this.add(customerDetails);

        if(this.getCustomersInTheSystem().getModel().getSize() >=1){
            Customer customer = this.getCustomersInTheSystem().getModel().getElementAt(0);

            this.getCustomerDetails().setText("Name: " + customer.getName() +"\n" +
                    "Customer-ID" + customer.getCustomerID());
        }



    }

    public JList<Customer> getCustomersInTheSystem() {
        return customersInTheSystem;
    }

    public JTextArea getCustomerDetails() {
        return customerDetails;
    }

    public void updateCustomerList(){
        this.customersInTheSystem.setListData(model.getCustomers().toArray(Customer[]::new));
//        this.customersInTheSystem.setListData((Customer[]) model.getCustomers().toArray());
        this.customersInTheSystemScrollPane.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        updateCustomerList();
    }
}
