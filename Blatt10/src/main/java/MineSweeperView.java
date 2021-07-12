import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class MineSweeperView extends JPanel implements Observer {

    private MineSweeper model;
    private int uncheckedBombs = 0;
    private MineSweeper.Mine[][] buttons;
    private JLabel bombLabel;
    public int currentX;
    public int currentY;

    public MineSweeperView(MineSweeper model){
        this.model = model;
        this.uncheckedBombs = model.getNumberBombs();
        this.setLayout(new BorderLayout());
        this.model.addObserver(this);

        JPanel field = new JPanel();
        field.setLayout(new GridLayout(model.getWidth(), model.getHeight()));

        JPanel bar = new JPanel();
        bar.setLayout(new FlowLayout(3));
        bombLabel =  new JLabel("Number of unchecked Bombs: " + uncheckedBombs);
        bar.add(bombLabel);
        JButton restart = new JButton();
        restart.addMouseListener(new restartListener());
        bar.add(restart);
        bar.add(new JLabel("Time"));

        this.add(field, BorderLayout.CENTER);
        this.add(bar, BorderLayout.NORTH);

        this.buttons  = model.getMineField();
        for(int i = 0; i < model.getWidth(); i++){
            for(int j = 0; j < model.getHeight(); j++){
                JButton current = buttons[i][j].button;

                field.add(current);
            }
        }
    }

    private class restartListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            new Main().makeGame();
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        if(model.getGameState() != model.RUNNING ){
            for(int i = 0; i < model.getWidth(); i++){
                for(int j = 0; j < model.getHeight(); j++){
                    if(!buttons[i][j].safe){
                        buttons[i][j].setIconResized(new ImageIcon(buttons[i][j].path+"bomb.png"));
                    }
                }
            }
            if(model.getGameState() == model.WON){

            }
        }
        bombLabel = new JLabel("Number of unchecked Bombs: " + model.getNumberBombs());
    }
}
