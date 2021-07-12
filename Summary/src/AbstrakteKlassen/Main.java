package AbstrakteKlassen;

public class Main {
    public static void main(String[] args) {
        Kreis kreis = new Kreis("Kreiselchen", 5);
        Quadrat quadrat = new Quadrat("Quadratschen", 5);
        kreis.draw();
        quadrat.draw();
    }
}
