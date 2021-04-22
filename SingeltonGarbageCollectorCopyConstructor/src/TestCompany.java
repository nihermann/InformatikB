/**
 *
 */
public class TestCompany {


    public static void testChangeStockPrice(){
        Company c = new Company("Tesla");
        c.changeStockPrice(20d);
        c.changeStockPrice(49.2d);
    }

    public static void testInsolvency() {
        Company c = new Company("Schlecker");
        c = null;
        System.gc();
        while(true){}

    }

    public static void runAll(){
        testChangeStockPrice();
        testInsolvency();
    }

    public static void main(String[] args){
        TestCompany.runAll();
    }
}
