package Inheritance;

public class InheritanceTest {
    public static void main(String[] args) {
        Student student = new Student("Michael", 41836);
        System.out.println(student.name);
        System.out.println(student.matrikelNr);
        // überschattung von namen attribut durch die superklasse
        System.out.println(((Person) student).name);

        // Shadowing:
        // die kind klasse implemenitiert attribute oder methoden die die elternklasse bereits besitzt.
        // Hier überlagert die Kinderklasse die methoden der Elternklasse
        // methoden werden hier dynamisch überschrieben, das heißt sie sind nicht mehr zu sehen
        // attribute werden nur überschattet, das heißt sie werden lediglich vom kinder attribut überdeckt sie
        // bleiben aber sichtbar
    }
}
