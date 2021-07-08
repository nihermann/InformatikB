package view;

import controller.CustomerDetailsController;
import model.logic.Customer;
import model.logic.CustomerSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CustomerDetailsPanel extends JPanel implements Observer {
    private final CustomerSystem model;

    private JList<Customer> customersInTheSystem;
    private JScrollPane customersInTheSystemScrollPane;
    private JTextArea customerDetail;

    public CustomerDetailsPanel(CustomerSystem model){
        this.model = model;
        model.addObserver(this);

        setBorder(BorderFactory.createLineBorder(Color.black));

        customersInTheSystemScrollPane = new JScrollPane();
        customersInTheSystemScrollPane.setPreferredSize(new Dimension(184, 300));
        add(customersInTheSystemScrollPane);

        customersInTheSystem = new JList<>(model.getCustomers().toArray(new Customer[10]));
        customersInTheSystem.addListSelectionListener(new CustomerDetailsController(this));
        customersInTheSystemScrollPane.add(customersInTheSystem);


        Customer firstCustomer = !model.getCustomers().isEmpty()?
                model.getCustomers().get(0)
                : null;
        String firstContent = firstCustomer != null?
                "Name: " + firstCustomer.getName() + "\nCustomer-ID: " + firstCustomer.getCustomerId()
                : "";
        customerDetail = new JTextArea(firstContent);
        customerDetail.setPreferredSize(new Dimension(184, 134));
        customerDetail.setEnabled(false);

        add(customerDetail);


    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     */
    @Override
    public void update(Observable o, Object arg) {
        updateCustomerList();
    }

    public void updateCustomerList(){
        customersInTheSystem.removeAll();
        customersInTheSystem.setListData(model.getCustomers().toArray(new Customer[0]));
        customersInTheSystem.setVisible(true);
    }

    public JList<Customer> getCustomersInTheSystem() {
        return customersInTheSystem;
    }

    public JTextArea getCustomerDetail() {
        return customerDetail;
    }

}
