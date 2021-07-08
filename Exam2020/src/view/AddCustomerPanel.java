package view;

import controller.AddCustomerController;
import model.logic.CustomerSystem;

import javax.swing.*;
import java.awt.*;

public class AddCustomerPanel extends JPanel {
    private final CustomerSystem model;

    private JLabel nameLabel;
    private JLabel customerIdLabel;
    private JTextField nameInput;
    private JTextField customerIdInput;
    private JButton addCustomer;

    public AddCustomerPanel(CustomerSystem model){
        this.model = model;

        setBorder(BorderFactory.createLineBorder(Color.black));

        nameLabel = new JLabel("Name");
        add(nameLabel);

        nameInput = new JTextField();
        nameInput.setPreferredSize(new Dimension(184, 84));
        add(nameInput);

        customerIdLabel = new JLabel("Customer-ID");
        add(customerIdLabel);

        customerIdInput = new JTextField();
        customerIdInput.setPreferredSize(new Dimension(184, 84));
        add(customerIdInput);

        addCustomer = new JButton("Add Customer");
        addCustomer.addActionListener(new AddCustomerController(model, this));
        add(addCustomer);

    }

    public JTextField getNameInput(){
        return nameInput;
    }

    public JTextField getCustomerIdInput(){
        return customerIdInput;
    }
}
