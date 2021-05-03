/**
 * A singleton for a ticker.
 * @Author Nicolai Hermann, Michael Hüppe
 */
public class Ticker {
    /**
     * Singleton instance of Ticker.
     */
    private static Ticker ticker;

    /**
     * Private constructor.
     */
    private Ticker(){}

    /**
     * returns the ticker.
     * @return ticker instance.
     */
    public static Ticker getTicker(){
        if (ticker == null){
            ticker = new Ticker();
        }
        return ticker;
    }

    /**
     * prints some text to the command line.
     * @param text String - text to print.
     */
    public void print(String text){
        System.out.print(" +++ " + text);
    }
}
