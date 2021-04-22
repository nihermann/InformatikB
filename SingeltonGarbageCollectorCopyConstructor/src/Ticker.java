/**
 *
 */
public class Ticker {
    /**
     *
     */
    private static Ticker ticker;

    /**
     *
     */
    private Ticker(){}

    /**
     *
     * @return
     */
    public static Ticker getTicker(){
        if (ticker == null){
            ticker = new Ticker();
        }
        return ticker;
    }

    /**
     *
     * @param text
     */
    public void print(String text){
        System.out.print(text.replaceAll("\n", "+++"));
    }
}
