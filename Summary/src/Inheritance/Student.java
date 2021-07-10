package Inheritance;

public class Student extends Person{
    String name;
    int matrikelNr;

    public Student(String name, int matrikelNr){
        this.name = name;
        super.name = "Supername";
        this.matrikelNr = matrikelNr;
    }

    public Student(){
        this("NA", 0);
    }
}
