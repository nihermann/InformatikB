import java.io.Serializable;

public class Student extends Person implements Serializable {
    private static int numStudents;
    private int matrikelNummer;
    private transient int vermögen;


    @Override
    public String toString() {
        return "Student{" +
                "matrikelNummer=" + matrikelNummer +
                ", vermögen=" + vermögen +
                ", name='" + name + '\'' +
                '}';
    }

    public Student(String name, int vermögen){
        super(name);
        matrikelNummer = numStudents++;
        this.vermögen = vermögen;
    }
}
