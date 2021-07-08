package aufg5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private final View v;
    private final Model m;

    public Controller(Model m, View v) {
        this.m = m;
        this.v = v;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        m.toggleSwitch();
        v.getOnButton().setEnabled(!m.isOn());
        v.getOffButton().setEnabled(m.isOn());
    }
}

