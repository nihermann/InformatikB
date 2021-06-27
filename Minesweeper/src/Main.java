public class Main {

    public static void main(String[] args) {
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        int numBombs = Integer.parseInt(args[2]);

        Scoreboard.init().registerEntry("Paula", 207.01);

        GameModel model = new GameModel(width, height, numBombs);
        GameView view = new GameView(model);

        view.display();

    }
}
