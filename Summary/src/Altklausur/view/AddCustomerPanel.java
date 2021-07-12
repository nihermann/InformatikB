package Altklausur.view;

import Altklausur.controller.AddCustomerController;
import Altklausur.model.logic.CustomerSystem;

import javax.swing.*;
import java.awt.*;

public class AddCustomerPanel extends JPanel{

    private static long serialVersionUID = 5L;

    private CustomerSystem model;
    private JLabel nameLabel;
    private JLabel customerIdLabel;
    private JTextField nameInput;
    private JTextField customerIdInput;
    private JButton addCustomer;

    public AddCustomerPanel(CustomerSystem model){
        this.model = model;
        this.nameLabel = new JLabel("Name");
        this.customerIdLabel = new JLabel("Customer-ID");

        this.nameInput = new JTextField();
        this.customerIdInput = new JTextField();
        this.nameInput.setPreferredSize(new Dimension(155,155));
        this.customerIdInput.setPreferredSize(new Dimension(155,155));

        this.addCustomer = new JButton("Add Customer");
        this.addCustomer.addActionListener(new AddCustomerController(this.model,this));

        this.add(this.nameLabel);
        this.add(this.nameInput);
        this.add(this.customerIdLabel);
        this.add(this.customerIdInput);
        this.add(this.addCustomer);
        super.setBorder(BorderFactory.createLineBorder(Color.black));

    }



    public JTextField getNameInput() {
        return nameInput;
    }

    public JTextField getCustomerIdInput() {
        return customerIdInput;
    }
}
