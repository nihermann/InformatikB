package view;

import model.logic.CustomerSystem;

import javax.swing.*;
import java.awt.*;

public class SystemPanel extends JPanel {
    private final CustomerSystem model;
    private final RemoveCustomerPanel removePanel;
    private final CustomerDetailsPanel detailsPanel;
    private final AddCustomerPanel addPanel;

    public SystemPanel(CustomerSystem model){
        this.model = model;
        this.removePanel = new RemoveCustomerPanel(model);
        this.detailsPanel = new CustomerDetailsPanel(model);
        this.addPanel = new AddCustomerPanel(model);

        setLayout(new BorderLayout());
        add(BorderLayout.WEST, addPanel);
        add(BorderLayout.CENTER, removePanel);
        add(BorderLayout.EAST, detailsPanel);
    }
}
