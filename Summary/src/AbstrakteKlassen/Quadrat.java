package AbstrakteKlassen;

public class Quadrat extends AbstrakteKlasse{
    public Quadrat(String name, int radius) {
        super(name, radius);
    }

    @Override
    public void draw() {
        for( int i = 0; i<=radius; i++){
            for(int j = 0; j<=radius; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
