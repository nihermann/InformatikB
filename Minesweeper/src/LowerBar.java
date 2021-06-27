import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class LowerBar extends JPanel implements Observer {

    private final GameModel model;

    private final JLabel gameStateLabel;
    private final JLabel highscoreLabel;
    private TextField nameField;

    private String name;

    private final Color highscoreColor = Color.orange;
    private final Font font = new Font("Arial", Font.BOLD, 32);



    public LowerBar(GameModel model){
        this.model = model;
        this.model.addObserver(this);

        setLayout(new GridLayout(1, 2));

        gameStateLabel = new JLabel("Waiting for first move!");
        gameStateLabel.setFont(font);
        add(gameStateLabel);

        nameField = new TextField(name = "Nicolai");
        nameField.addActionListener(e -> name = nameField.getText());
        nameField.setFont(font);
        add(nameField);

        highscoreLabel = new JLabel();
        highscoreLabel.setFont(font);
        highscoreLabel.setForeground(highscoreColor);
        updateScoreBoard();
        add(highscoreLabel);

    }

    public String getUserName() {
        return name;
    }

    void updateScoreBoard() {
        ScoreboardEntry sbe = Scoreboard.init().getHighscore();
        this.highscoreLabel.setText("Highscore: " + (sbe!=null? (sbe.name() + " - " + sbe.time()) : "N.N."));
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
        if(GameModel.REINIT.equals(arg)) {
            gameStateLabel.setText("Waiting for first move!");
        } else {
            gameStateLabel.setText("Game state: " + model.getGameState());
        }
    }
}
