package Generics;

public class Shelter <T extends Dog>{
    T animal1;
    T animal2;

    public Shelter(T animal1, T animal2){
        this.animal1 = animal1;
        this.animal2 = animal2;
    }

    public T adopt(T animal1, T animal2){
        return Math.random() > 0.5? animal1:animal2;
    }

    public static void main(String[] args) {
        Labradoodle labradoodle = new Labradoodle("Lilou", 20);
        Labradoodle labradoodle1 = new Labradoodle("Olli", 17);

        Shelter shelter = new Shelter(labradoodle1, labradoodle);
        shelter.adopt(labradoodle1,labradoodle).bark();
    }
}
