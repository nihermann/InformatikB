/**
 * This class tests the functionality of Person and Students equals methods.
 * @Author Michael Hüppe, Nicolai Hermann
 */
import EqualsAndHashCode.*;
import static Test.Test.*;

public class TestEquals {
    /**
     * Tests whether or not a equals method is reflexive
     * Reflexive: for any non-null reference value x, x.equals(x) should return true.
     * Meaning that an object equals itself.
     *
     * Throws Exception if equals fails.
     *
     */

    public static void testReflexive(){
        Student student = new Student("Max", 1);
        Student Test = new Student("Max", 1);
        shouldBeTrue(student.equals(student),"The equals method for Students does not confirm to reflexivity.");
        shouldBeTrue(Test.equals(Test),"The equals method for Person does not confirm to reflexivity.");
    }

    /**
     * Tests whether or not a equals method is symmetric.
     * Symmetric: for any non-null reference values x and y, x.equals(y) should return true iff y equals.(x) returns true as well.
     */
    public static void testSymmetric(){
        Student student = new Student("Max",1);
        Student student1 = new Student("Max",1);
        Person person = new Person("Max");
        Person person1 = new Person("Max");
        shouldBeTrue(student.equals(student1) == student1.equals(student) ,"The equals method for Students does not confirm to symmetry.");
        shouldBeTrue(person.equals(person1) == person1.equals(person) ,"The equals method for Person does not confirm to symmetry.");
        shouldBeTrue(student.equals(person) == person.equals(student) ,"The equals method between Sub and Superclass does not confirm to symmetry.");

    }

    /**
     * Tests whether or not a equals method is transitive.
     * Transitivity: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true,
     * then x.equals(z) also returns true
     */
    public static void transitivity(Object x, Object y, Object z, String errorMsg){
        shouldBeTrue(x.equals(y) == y.equals(z) == x.equals(z),errorMsg);
    }

    public static void testTransitive(){
        Student student = new Student("Max",1); //x
        Student student1 = new Student("Max",1); //y
        Student student2 = new Student("Max",1); //z
        Person person = new Person("Max"); // x/z
        Person person1 = new Person("Max"); //y
        Person person2= new Person("Max"); //z

        transitivity(student, student1, student2,"The equals method for Students does not confirm to transitivity.");
        transitivity(student, student1, person,"The equals method for Students and Person does not confirm to transitivity.");
        transitivity(person, person1, person2,"The equals method for Students does not confirm to transitivity.");

    }

    /**
     * Tests whether or not a equals method is consitent.
     * Consistency: It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y)
     * consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.
     */
    public static void testConsistent(){
        Student student = new Student("Max",1);
        Student student1 = new Student("Max",1);
        Student student2 = new Student("Maxine",1);
        int i=0;
        while (i<200){
            shouldBeTrue(student.equals(student1) == student1.equals(student),"The equals method for Students does not confirm to consistency.");
            shouldBeTrue(student.equals(student1) != student2.equals(student),"The equals method for Students does not confirm to consistency.");

            i++;
        }
    }

    /**
     * Tests whether or not a equals method returns false for every non-null object comparison.
     * Non-Null: For any non-null reference value x, x.equals(null) should return false.
     */
    public static void testNonNull(){
        Student student = new Student("Max",1);
        shouldBeTrue(!student.equals(null),"The equals method for Students does not return false when comparing with null.");

    }

    /**
     * Tests whether or not a equals method is symmetric.
     * Symmetric: for any non-null reference values x and y, x.equals(y) should return true iff y equals.(x) returns true aswell.
     */
    public static void testUpdatedSymmetric(){
        Student student = new Student("Max",1);
        Student student1 = new Student("Max",1);
        Person person = new Person("Max");
        shouldBeTrue(student.symmetricEquals(student1) == student1.symmetricEquals(student) ,"The symmetricEquals method does not confirm to symmetry.");
        shouldBeTrue(student.symmetricEquals(person) == person.symmetricEquals(student) ,"The symmetricEquals method does not confirm to symmetry.");

    }

    /**
     * Tests whether or not a equals method is symmetric.
     * Symmetric: for any non-null reference values x and y, x.equals(y) should return true iff y equals.(x) returns true aswell.
     */
    public static void testHashcode(){
        Student student = new Student("Max",1);
        Student student1 = new Student("Max",1);
        Person person = new Person("Max");
        Person person1 = new Person("Max");
        shouldBeTrue(student.hashCode() == student1.hashCode() ,"The hashCode method does not work for Students.");
        shouldBeTrue(person.hashCode() == person1.hashCode() ,"The hashCode method does not work for Students.");

    }

    public static void main(String[] args) {
        testReflexive();
        // instanceOf also takes class hierarchies into consideration thus student is seen as a Person
        // therefore the equals method checks for a same name and if that is given the method mistakenly returns true
        testSymmetric();
        testTransitive();
        testNonNull();
        testUpdatedSymmetric();
        testConsistent();
        testHashcode();
        System.out.println("Errors found while testing: " + getErrorCount());
    }

}
