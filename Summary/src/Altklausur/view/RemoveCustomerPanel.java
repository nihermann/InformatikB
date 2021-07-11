package Altklausur.view;

import Altklausur.controller.RemoveCustomerController;
import Altklausur.model.logic.CustomerSystem;

import javax.swing.*;
import java.awt.*;

public class RemoveCustomerPanel extends JPanel {
    private static long serialVersionUID = 5L;

    private JButton removeCustomer;
    private JTextField customerIdToRemove;
    private JLabel customerIdToRemoveLabel;
    private CustomerSystem model;

    public RemoveCustomerPanel(CustomerSystem model){
        this.model = model;
        this.customerIdToRemoveLabel = new JLabel("Customer-ID to remove:");
        this.customerIdToRemoveLabel.setPreferredSize(new Dimension(155,155));
        this.add(customerIdToRemoveLabel);
        this.customerIdToRemove = new JTextField();
        this.customerIdToRemove.setPreferredSize(new Dimension(155,155));
        this.add(customerIdToRemove);
        this.removeCustomer = new JButton("Remove Customer");
        this.add(this.removeCustomer);
        this.removeCustomer.addActionListener(new RemoveCustomerController(this.model, this));
        super.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public JTextField getCustomerIdToRemove() {
        return customerIdToRemove;
    }
}
