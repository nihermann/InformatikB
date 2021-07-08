package controller;

import model.logic.Customer;
import view.CustomerDetailsPanel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class CustomerDetailsController implements ListSelectionListener {
    private final CustomerDetailsPanel view;

    public CustomerDetailsController(CustomerDetailsPanel view){
        this.view = view;
    }
    /**
     * Called whenever the value of the selection changes.
     *
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        Customer selected = view.getCustomersInTheSystem().getSelectedValue();
        if(selected == null) {
            List<Customer> all = view.getCustomersInTheSystem().getSelectedValuesList();
            if(all.isEmpty()){
                view.getCustomerDetail().setText("");
                return;
            }
            selected = all.get(0);
        }
        String content = "Name: " + selected.getName() + "\nCustomer-ID: " + selected.getCustomerId();
        view.getCustomerDetail().setText(content);
    }
}
