package MVC;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {
    private final Model model;

    private JButton btn1;

    /**
     *
     * @param model logical model.
     */
    public View(Model model) {
        this.model = model;
        model.addObserver(this);

        setTitle("INSERT");
        setSize(800, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container main = getContentPane();

        // Button
        btn1 = new JButton("INSERT");
        btn1.setEnabled(true);
        btn1.setForeground(Color.green);
        btn1.setBackground(Color.BLACK);
        btn1.setBorder(BorderFactory.createLineBorder(Color.white));

        btn1.addActionListener(new Controller(model, this));
        main.add(btn1);



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

    }
}
