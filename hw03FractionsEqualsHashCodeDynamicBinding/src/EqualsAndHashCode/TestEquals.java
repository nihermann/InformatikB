public class TestEquals {
    private static Test test;
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
        test.shouldBeTrue(student.equals(student),"The equals method for Students does not confirm to refelxivity.");
    }

    /**
     * Tests whether or not a equals method is symmetric.
     * Symmetric: for any non-null reference values x and y, x.equals(y) should return true iff y equals.(x) returns true aswell.
     */
    public static void testSymmetric(){
        Student student = new Student("Max",1);
        Student student1 = new Student("Max",1);
        Person person = new Person("Max");
        test.shouldBeTrue(student.equals(student1) == student1.equals(student) ,"The equals method for Students does not confirm to symmetry.");
        test.shouldBeTrue(student.equals(person) == person.equals(student) ,"The equals method for Students does not confirm to symmetry.");

    }

    /**
     * Tests whether or not a equals method is transitive.
     * Transitiviy: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true,
     * then x.equals(z) also returns true
     */
    public static void testTransitive(){
        Student student = new Student("Max",1); //x
        Student student1 = new Student("Max",1); //y
        Student student2 = new Student("Max",1); //z
        Person person = new Person("Max"); //z

//                                 x(y)                         y(z)                    x(z)
        test.shouldBeTrue(student.equals(student1) == student1.equals(person) == student.equals(person),"The equals method for Students does not confirm to transitivity.");

    }

    /**
     * Tests whether or not a equals method is consitent.
     * Consistencs: It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.
     */
    public static void testConsistent(){
        Student student = new Student("Max",1);
        Student student1 = new Student("Max",1);
        Student student2 = new Student("Maxine",1);
        int i=0;
        while (i<200){
            test.shouldBeTrue(student.equals(student1) == student1.equals(student),"The equals method for Students does not confirm to consitency.");
            test.shouldBeTrue(student.equals(student1) != student2.equals(student),"The equals method for Students does not confirm to consitency.");

            i++;
        }
    }

    /**
     * Tests whether or not a equals method returns false for every non-null object comparison.
     * Non-Null: For any non-null reference value x, x.equals(null) should return false.
     */
    public static void testNonNull(){
        Student student = new Student("Max",1);
        test.shouldBeTrue(!student.equals(null),"The equals method for Students does not retun false when comparing with null.");

    }

    /**
     * Tests whether or not a equals method is symmetric.
     * Symmetric: for any non-null reference values x and y, x.equals(y) should return true iff y equals.(x) returns true aswell.
     */
    public static void testUpdatedSymmetric(){
        Student student = new Student("Max",1);
        Student student1 = new Student("Max",1);
        Person person = new Person("Max");
        test.shouldBeTrue(student.symmetricEquals(student1) == student1.symmetricEquals(student) ,"The symmetricEquals method does not confirm to symmetry.");
        test.shouldBeTrue(student.symmetricEquals(person) == person.symmetricEquals(student) ,"The symmetricEquals method does not confirm to symmetry.");

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
        test.printErrorCount();
    }

}
