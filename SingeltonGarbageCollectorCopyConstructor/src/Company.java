/**
 *
 */
public class Company {
    /**
     *
     */
    private String name;

    /**
     *
     * @param name
     */
    public Company(String name){
        this.name = name;
    }

    /**
     *
     * @param d
     */
    public void changeStockPrice(double d){
        Ticker.getTicker().print("\n" + this.name + " " + d);
    }

    /**
     *
     */
    public void finalize(){
        Ticker.getTicker().print("\n" + this.name + " is insolvent");
    }
}
