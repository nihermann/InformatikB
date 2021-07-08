package view;

import controller.RemoveCustomerController;
import model.logic.CustomerSystem;

import javax.swing.*;
import java.awt.*;

public class RemoveCustomerPanel extends JPanel {
    private final CustomerSystem model;
    private JButton removeCustomer;

    private JTextField customerIdToRemove;
    private JLabel customerIdToRemoveLabel;

    public RemoveCustomerPanel(CustomerSystem model){
        this.model = model;

        setBorder(BorderFactory.createLineBorder(Color.black));

        customerIdToRemoveLabel = new JLabel("Customer-ID to remove");
        add(customerIdToRemoveLabel);

        customerIdToRemove = new JTextField();
        customerIdToRemove.setPreferredSize(new Dimension(184, 84));
        add(customerIdToRemove);

        removeCustomer = new JButton("Remove Customer");
        removeCustomer.addActionListener(new RemoveCustomerController(model, this));
        add(removeCustomer);

    }

    public JTextField getCustomerIdToRemove() {
        return customerIdToRemove;
    }
}
