package SerialisierungKlausur;

import java.io.Serializable;

public class Student extends Person implements Serializable {
    private static long serialVersionUID = 1L;

    private transient int vermoegen;
    private int matrikelNummer;
    private static int matrikelNummerCount;

    public Student(String name, int vermoegen){
        super(name);
        this.vermoegen = vermoegen;
        this.matrikelNummer = matrikelNummerCount;
        matrikelNummerCount++;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name= " + name +
                " vermoegen=" + vermoegen +
                ", matrikelNummer=" + matrikelNummer +
                '}';
    }
}
