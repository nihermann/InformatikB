import javax.swing.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new Main().makeGame();
    }

    public void makeGame(){
        MineSweeper mineSweeper = new MineSweeper(40,16, 16);

        mineSweeper.checkNeighbours(1,0);

        JFrame frame = new JFrame();
        MineSweeperView mineSweeperView = new MineSweeperView(mineSweeper);

        frame.setContentPane(mineSweeperView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
