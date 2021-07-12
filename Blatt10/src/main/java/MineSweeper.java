import javax.swing.*;
import java.awt.*;
import java.net.PortUnreachableException;
import java.util.Observable;

import static java.util.concurrent.ThreadLocalRandom.current;

public class MineSweeper extends Observable {

    private int numberBombs;
    private int width;
    private int height;
    private int uncheckedFields;

    // game state
    public static final int LOST = -1;
    public static final int RUNNING = 0;
    public static final int WON = 1;
    private int gameState = RUNNING;

    private Mine[][] mineField;

    class Mine{
        /**
         * Inner class implementing a simple button with a additional flag (safe)
         */
        String path = "C:\\Users\\michi\\Osnabrueck\\4_Semester\\Software_entwicklung\\InformatikB\\Blatt10\\src\\main\\resources\\";

        public JButton button;
        public boolean safe;
        // int position
        public int x,y;
        // boolean to determine whether or not the field has been checked or flagged yet
        public boolean checked,flagged = false;

        // number of surrounding Neighbours that are bombs
        public int bombNeighbours;

       public Mine(boolean safe, int x , int y){
           this.button = new JButton();
           this.button.setPreferredSize(new Dimension(30,30));
           this.button.setBorderPainted(false);
           this.button.addMouseListener(new MineSweeperController(this));
           this.safe = safe;
           this.x =x;
           this.y = y;
           setIconResized(new ImageIcon(path+"undiscovered_Field.png"), 30,30);
       }

        public void setBombNeighbours(int bombNeighbours) {
            this.bombNeighbours = bombNeighbours;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public void setIconResized(ImageIcon icon, int width , int height){
            Image img = icon.getImage() ;
            Image newimg = img.getScaledInstance(width,height, java.awt.Image.SCALE_SMOOTH ) ;
            icon = new ImageIcon( newimg );
            button.setIcon(icon);
        }

        public void setIconResized(ImageIcon icon){
            setIconResized(icon, button.getWidth(),button.getHeight());
        }

        public void check(){
            if(!safe){
                gameState = LOST;
            }else if(uncheckedFields==0){
                gameState = WON;
            }else {
                System.out.println("Ich bin in dem running drin");
                setIconResized(new ImageIcon(path + bombNeighbours + ".jpeg"));

                if(!checked){
                    checked = true;
                    if(safe && bombNeighbours == 0){
                        for (int i = -1; i <= 1; i++) {
                            for (int j = -1; j <= 1; j++) {
                                // No need to look at our current position.
                                if ((i == 0 && j == 0) || !inField(x+i, y+j) ) continue;
                                if(safe&&bombNeighbours == 0){
                                    mineField[x+i][y+j].check();
                                }
                            }
                        }
                    }
                    uncheckedFields--;
                }
            }

            System.out.println("Number of fields left undiscovererd: " + uncheckedFields);
            setChanged();
            notifyObservers();
        }

        public void flag(){
           this.flagged = true;
            setNumberBombs(numberBombs-1);

            setIconResized(new ImageIcon(path + "flag.png"));
        }
    }

    /**
     * Initalized the mineSweeper instance and creates a field.
     * If only one dimension is given the field is instantiated to be quadratic.
     * If no dimensions for the field are given the default is 20.
     * If no parameters are given the number of Bombs default to 10.
     *
     * @param numberBombs which are supposed to be placed in the field
     * @param width of the field
     * @param height of the field
     */
    public MineSweeper(int numberBombs, int width, int height){
        this.numberBombs = numberBombs;
        this.width = width;
        this.height = height;
        this.uncheckedFields = width*height - numberBombs;
        this.mineField = new Mine[width][height];
        createField();
    }

    public MineSweeper(int numberBombs, int size){
        this(numberBombs,size, size);
    }

    public MineSweeper(int numberBombs){
        this(numberBombs,20,20);
    }

    public MineSweeper(){
        this(10,20,20);
    }

    /**
     * Creates a Field for the current instance in the form of a two dimensional integer array.
     */
    private void createField(){
        if(numberBombs > width*height){
            throw new RuntimeException("The field is too small for the given amount you have entered.");
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                mineField[x][y] = new Mine(true, x,y);
            }
        }

        setBombs();
        setBombsNeighbours();

        this.setChanged();
        this.notifyObservers();
    }

    /**
     * sets the number of bombs on the mine field
     */
    private void setBombs(){
        int temp = numberBombs;
        while(temp>0){
            int x = current().nextInt(0, width );
            int y = current().nextInt(0, height);
            if(mineField[x][y].safe){
                mineField[x][y].safe = false;
                temp--;
            }
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return boolean checks whether or not the element with the given cordiantes is a bomb or not
     */
    public boolean bomb(int x, int y){
        return !mineField[x][y].safe;
    }

    /**
     * @param x
     * @param y
     * @return boolean whether or not the given coordinates still lie in the field
     */
    public boolean inField(int x, int y){
        return x < width && y < height && x >= 0 && y >= 0;
    }

    /**
     * Checks the surroundings of the given element and counts the bombs.
     *
     * @param x coordinate of the element which neighbours you want to check
     * @param y coordinate of the element which neighbours you want to check
     */
    public int checkNeighbours(int x, int y) {
        int bombCount = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // No need to look at our current position.
                if ((i == 0 && j == 0) || !inField(x+i, y+j) ) continue;
                if(bomb(x+i,y+j)){
                    bombCount++;
                }
            }
        }
        return bombCount;
    }

    /**
     * sets the number of sorrounding bomb for each field
     */
    public void setBombsNeighbours(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                mineField[x][y].bombNeighbours = checkNeighbours(x,y);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumberBombs() {
        return numberBombs;
    }

    public void setNumberBombs(int numberBombs) {
        this.numberBombs = numberBombs;
    }

    public Mine[][] getMineField() {
        return mineField;
    }

    public int getGameState() {
        return gameState;
    }
}
