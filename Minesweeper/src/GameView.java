import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GameView extends JFrame implements Observer {

    private final GameModel model;
    private final JButton[][] buttons;

    private LowerBar lowerBar;

    Timer timer;

    private final Color[] revealedColor = {new Color(229, 194, 159), new Color(215, 184, 153)};
    private final Color[] hiddenColor = {new Color(170, 215, 81), new Color(162, 209, 73)};
    private final Color markedColor = new Color(219, 50, 54);


    private final Color[] textColor = {
            Color.black,
            new Color(25, 118, 210), new Color(56, 142, 60),
            new Color(212, 62, 59), new Color(123, 31, 162),
            new Color(72, 203, 241), new Color(237, 68,181),
            new Color(244, 132, 13),  new Color(219, 50, 54)
    };

    private final Font font = new Font("Arial", Font.BOLD, 32);



    public GameView(GameModel model){
        this.model = model;
        model.addObserver(this);

        setSize(1200, 1200);
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        GridLayout mainGrid = new GridLayout(3, 1);
        this.getContentPane().setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.setMaximumSize(new Dimension(this.getWidth(), (int) (this.getHeight()*0.1)));

        JLabel label = new JLabel(model.getWidth() + "x" + model.getHeight() + "  Mines: " + model.getNumOfBombs());
        JLabel timerLabel = new JLabel(String.valueOf(model.getTime()));
        label.setFont(font);
        timerLabel.setFont(font);
        topPanel.add(label);
        topPanel.add(timerLabel);

        timer = new Timer(timerLabel, model);

        this.getContentPane().add(BorderLayout.NORTH, topPanel);

        GridLayout grid = new GridLayout(model.getWidth(), model.getHeight());
        buttons = new JButton[model.getWidth()][model.getHeight()];

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(grid);

        for (int j = 0; j < model.getHeight(); j++){
            for (int i = 0; i < model.getWidth(); i++){
                JButton btn = new JButton();
                btn.addMouseListener(new InputController(model, i, j));
                btn.setBackground(hiddenColor[(i+j) % 2]);
                btn.setBorderPainted(false);
                btn.setFont(font);
                btn.setPreferredSize(new Dimension(20,20));

                btnPanel.add(i + "x" + j, btn);
                buttons[i][j] = btn;
            }
        }

        btnPanel.setBackground(new Color(150,150,150));
        this.getContentPane().add(BorderLayout.CENTER, btnPanel);


        lowerBar = new LowerBar();
        this.getContentPane().add(BorderLayout.SOUTH, lowerBar);

    }

    public void display(){
        this.setVisible(true);
    }

    private void adaptButton(Field f, JButton b, int x, int y){
        int nearBombs = f.getBombsNearBy();
        b.setText(
            f.isRevealed()?
                    String.valueOf(nearBombs > 0? nearBombs : "")
                    : f.isMarked()? "X" : ""
        );

        b.setForeground(
            f.isMarked()?
                    this.markedColor
                    : this.textColor[f.getBombsNearBy()]
        );

        b.setBackground(
            f.isRevealed()? this.revealedColor[(x+y)%2] : this.hiddenColor[(x+y)%2]
        );

        b.setBorderPainted(f.isMarked());
        b.setBorder(BorderFactory.createLineBorder(this.markedColor, 3));

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
        if(model.isRunning()) {
            if(!timer.isAlive()) timer.start();

            Field[][] board = model.getBoard();
            for (int j = 0; j < model.getHeight(); j++) {
                for (int i = 0; i < model.getWidth(); i++) {
                    this.adaptButton(board[i][j], this.buttons[i][j], i, j);
                }
            }

        } else{
            if(model.hasWon()){
                System.out.println("CONGRATS YOU WON");
                Field[][] board = model.getBoard();
                for (int j = 0; j < model.getHeight(); j++) {
                    for (int i = 0; i < model.getWidth(); i++) {
                        this.adaptButton(board[i][j], this.buttons[i][j], i, j);
                        if (board[i][j].isMarked()){
                            this.buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.green, 3));
                            this.buttons[i][j].setForeground(Color.green);
                        }
                    }
                }

                Scoreboard.init().registerEntry(lowerBar.getUserName(), timer.getEndTime());
                this.lowerBar.updateScoreBoard();

            }else{
                System.out.println("LOOOOOOOOSER");

                // show all bombs
                Field[][] board = model.getBoard();
                for (int j = 0; j < model.getHeight(); j++) {
                    for (int i = 0; i < model.getWidth(); i++) {
                        Field current = board[i][j];
                        this.buttons[i][j].setText(current.isBomb()? "Bomb" : "");
                        this.buttons[i][j].setForeground(markedColor);
                    }
                }
            }
        }
    }
}
