import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LowerBar extends JPanel {

    private final JLabel highscoreLabel;
    private TextField nameField;

    private String name;

    private final Color highscoreColor = Color.orange;
    private final Font font = new Font("Arial", Font.BOLD, 32);



    public LowerBar(){
        setLayout(new GridLayout(1, 2));

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
}
