package Singleton;

public class Singleton {
    private static Singleton instance;

    private int attr;
    private String string;

    /**
     * Private constructor such that it is not possible to create instances outside this class.
     */
    private Singleton(){

    }

    /**
     * @return the single instance of this class.
     */
    public Singleton getInstance(){
        if (instance == null) instance = new Singleton();
        return instance;
    }

}
