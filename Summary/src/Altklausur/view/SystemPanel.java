package Altklausur.view;

import Altklausur.model.logic.CustomerSystem;

import javax.swing.*;
import java.awt.*;

public class SystemPanel extends JPanel {
    private static long serialVersionUID = 5L;

    private CustomerSystem model;
    private AddCustomerPanel addCustomerPanel;
    private RemoveCustomerPanel removeCustomerPanel;
    private CustomerDetailsPanel customerDetailsPanel;

    public SystemPanel(CustomerSystem model){
        this.model = model;
        this.addCustomerPanel = new AddCustomerPanel(this.model);
        this.removeCustomerPanel = new RemoveCustomerPanel(this.model);
        this.customerDetailsPanel = new CustomerDetailsPanel(this.model);

        this.setLayout(new BorderLayout());
        this.add(this.addCustomerPanel, BorderLayout.WEST);
        this.add(this.removeCustomerPanel, BorderLayout.CENTER);
        this.add(this.customerDetailsPanel, BorderLayout.EAST);

    }
}
