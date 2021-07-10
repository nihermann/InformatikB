package AbstrakteKlassen;

public abstract class AbstrakteKlasse {
    String name;
    int radius;

    public AbstrakteKlasse(String name, int radius){
        this.name = name;
        this.radius = radius;
    }

    public abstract void draw();
}
