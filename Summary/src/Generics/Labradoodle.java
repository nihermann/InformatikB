package Generics;

public class Labradoodle implements Dog{
    private String name;
    final static String breed = "Labradoodle";
    private int tailLength;

    public Labradoodle(String name, int tailLength){
        this.name = name;
        this.tailLength = tailLength;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public int getTailLenght() {
        return tailLength;
    }

    @Override
    public void bark() {
        System.out.println("Woof ich bin der " + name + " !");
    }
}
