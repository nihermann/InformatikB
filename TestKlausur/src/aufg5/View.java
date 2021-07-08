package aufg5;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {
    private final Model model;

    public JButton getOnButton() {
        return onButton;
    }

    public JButton getOffButton() {
        return offButton;
    }

    private JButton light;
    private JButton onButton;
    private JButton offButton;

    public View(Model model) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.model = model;
        model.addObserver(this);
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        setTitle("Lichtschalter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(200,200));
        add(mainPanel);
        light = new JButton("");
        light.setEnabled(false);
        light.setBackground(Color.black);
        mainPanel.add(light);

        onButton = new JButton("Licht an");
        onButton.setForeground(Color.green);
        onButton.addActionListener(new Controller(model, this));
        mainPanel.add(onButton);

        offButton = new JButton("Licht aus");
        offButton.setForeground(Color.red);
        offButton.setEnabled(false);
        offButton.addActionListener(new Controller(model,this));
        mainPanel.add(offButton);


        pack();
        setVisible(true);
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
        Color c = model.isOn()? Color.yellow : Color.black;
        light.setBackground(c);
    }
}
