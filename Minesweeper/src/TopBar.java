import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TopBar extends JPanel implements Observer {
    private final GameView parent;
    private final GameModel model;

    private final JLabel bombsLeftLabel;
    private final JLabel timerLabel;

    Timer timer;

    private final Font font = new Font("Arial", Font.BOLD, 32);

    TopBar(GameView gameView, GameModel model) {
        parent = gameView;
        this.model = model;
        model.addObserver(this);

        setLayout(new GridLayout(1, 3));
        setMaximumSize(new Dimension(this.getWidth(), (int) (this.getHeight()*0.1)));

        bombsLeftLabel = new JLabel("Bombs left: " + model.getNumOfBombs());
        bombsLeftLabel.setFont(font);
        add(bombsLeftLabel);


        JLabel label = new JLabel(model.getWidth() + "x" + model.getHeight() + "  Mines: " + model.getNumOfBombs());
        label.setFont(font);
        add(label);


        timerLabel = new JLabel("Time: 0");
        timerLabel.setFont(font);
        add(timerLabel);

        timer = new Timer(timerLabel, model);
    }

    public double getEndTime(){ return timer.getEndTime();}

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
        if(GameModel.REINIT.equals(arg)){
            timer = new Timer(timerLabel, model);
            timerLabel.setText("Time: 0");
            bombsLeftLabel.setText("Bombs left: " + model.getNumOfBombs());
            return;
        }
        if(model.getGameState().equals(GameModel.RUNNING)) {
            if (!timer.isAlive()) timer.start();
            bombsLeftLabel.setText("Bombs left: " + model.bombsLeft());
        }
    }
}
