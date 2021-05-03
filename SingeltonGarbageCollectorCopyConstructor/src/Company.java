/**
 * @Author Nicolai Hermann, Michael Hüppe.
 */
public class Company {
    /**
     * The name of the company.
     */
    private String name;

    /**
     * Constructs a company with given name.
     * @param name string - name of the company.
     */
    public Company(String name){
        this.name = name;
    }

    /**
     * Changes the stock price of the company which will also be logged.
     * @param d double - the new value of the company's stock price.
     */
    public void changeStockPrice(double d){
        Ticker.getTicker().print(this.name + " " + d);
    }

    /**
     * If the company is deleted it will get logged as the company is insolvent.
     */
    public void finalize(){
        Ticker.getTicker().print( this.name + " is insolvent");
    }
}
