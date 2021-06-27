import java.util.Observable;
import java.util.Random;

public class GameModel extends Observable {
    public static final String RUNNING = "running";
    public static final String WON = "won";
    public static final String LOST = "lost";
    public static final String REINIT = "reinit";

    private final int width, height, numOfBombs;
    private int fieldsUntilWin, numMarked;

    private boolean isRunning = true;

    private Field[][] board;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumOfBombs() {
        return numOfBombs;
    }

    public Field[][] getBoard() {
        return board;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean hasWon() {
        return fieldsUntilWin == 0;
    }

    public int bombsLeft(){ return numOfBombs - numMarked; }

    public GameModel(int width, int height, int numOfBombs) {
        if (numOfBombs > width * height || numOfBombs < 1) {
            throw new IllegalArgumentException("Having more bombs that fields or less than 1 is not reasonable.");
        }
        this.width = width;
        this.height = height;
        this.numOfBombs = numOfBombs;
        this.fieldsUntilWin = (width*height) - numOfBombs;
        board = new Field[width][height];
        this.initGame(numOfBombs);
    }

    private void initGame(int numOfBombs) {
        Random r = new Random();
        for (int w = 0; w < this.width; w++){
            for (int h = 0; h < this.height; h++){
                this.board[w][h] = new Field();
            }
        }

        // place bombs
        while (numOfBombs > 0){
            int w = r.nextInt(this.width);
            int h = r.nextInt(this.height);
            if(!this.board[w][h].isBomb()){
                this.board[w][h].setBomb();
                this.initUpdateMoore(w, h);
                numOfBombs--;
            }
        }
    }

    public void reinit(){
        this.initGame(numOfBombs);
        numMarked = 0;
        fieldsUntilWin = (width*height) - numOfBombs;
        isRunning = true;

        this.setChanged();
        this.notifyObservers("reinit");
    }

    private void initUpdateMoore(int xBomb, int yBomb){
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0) continue;
                if (xBomb + i < 0 || xBomb + i >= this.width) continue;
                if (yBomb + j < 0 || yBomb + j >= this.height) continue;
                this.board[xBomb + i][yBomb + j].incrementBombsNearBy();
            }
        }
    }

    public void updateField(int x, int y){
        this.updateField(x, y, false);
    }

    public void updateMarked(int x, int y){
        this.updateField(x, y, true);
    }

    private void updateField(int x, int y, boolean onlyMark){
        if(x < 0 || x >= this.width || y < 0 || y >= this.height){
            throw new IllegalStateException(
                    "x or y not on the board. width x height: " + this.width + " x " +
                            this.height + " found x - y: " + x + " - " + y
            );
        }
        Field current = this.board[x][y];

        // dont do anything if the Field is already revealed.
        if(current.isRevealed()) return;

        // if right click we want to toggle the marker.
        if(onlyMark){
            if (current.toggleMarked()) numMarked++;
            else numMarked--;

            this.setChanged();
            this.notifyObservers();
            return;
        }

        // return if left click on marked field.
        if(current.isMarked()) return;

        // if clicked on non marked field which is a bomb, the game will be over.
        if(current.isBomb()){
            this.isRunning = false;
            this.setChanged();
            this.notifyObservers();
            return;
        }

        // if current has no bomb in its neighbourhood we want to reveal or show more fields.
        this.makeVisible(x, y);
        this.setChanged();
        this.notifyObservers();
    }

    private void makeVisible(int x, int y) {
        if(this.board[x][y].getBombsNearBy() > 0){
            reveal(this.board[x][y]);
            return;
        }

        reveal(this.board[x][y]);

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                // check if we exceeded the borders:
                if (x + i < 0 || x + i >= this.width) continue;
                if (y + j < 0 || y + j >= this.height) continue;

                if (!this.board[x+i][y+j].isRevealed()) makeVisible(x+i, y+j);
            }
        }
    }

    private void reveal(Field field) {
        field.setRevealed();
        this.countDown();
        if (field.isMarked()){
            field.toggleMarked();
            numMarked--;
        }
    }

    private void countDown() {
        this.fieldsUntilWin--;
        if (this.fieldsUntilWin == 0) this.isRunning = false;
    }

    public String getGameState(){
        return isRunning()? RUNNING : hasWon()? WON : LOST;
    }
}
