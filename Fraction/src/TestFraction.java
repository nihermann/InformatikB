public class TestFraction {
    public static void validate(boolean expression){
        System.out.println(expression? "Success" : "Test failed!");
    }

    public static void denominator_should_Be_1_when_initialized_with_one_parameter() {
        System.out.print("Testing: denominator should be 1 when initialized with one parameter... ");

        Fraction f = new Fraction(10);

        validate(f.getDenominator() == 1);
    }

    public static void fraction_was_reduced_after_initialization(){
        System.out.print("Testing: Fraction should get reduced after initialization...");
        boolean success = true;

        Fraction f1 = new Fraction(2, 4);
        success = success && (f1.getNumerator() == 1);
        success = success && (f1.getDenominator() == 2);

        Fraction f2 = new Fraction(3, 9);
        success = success && (f2.getNumerator() == 1);
        success = success && (f2.getDenominator() == 3);

        validate(success);
    }

    public static void multiplication_with_factor_is_correct(){
        System.out.print("Testing: multiplication with factor is correct... ");

        Fraction f1 = new Fraction(3, 4);
        f1 = f1.multiply(5);

        validate((f1.getNumerator() == 15) && (f1.getDenominator() == 4));
    }

    public static void multiplication_with_fraction_is_correct(){
        System.out.print("Testing: multiplication with fraction is correct... ");
        Fraction a = new Fraction(3, 4);
        Fraction b = new Fraction(1, 4);

        Fraction result = a.multiply(b);

        validate((result.getNumerator() == 3) && (result.getDenominator() == 16));
    }

    public static void multiplication_with_multiple_fractions_is_correct(){
        System.out.print("Testing: multiplication with multiple fractions is correct... ");

        Fraction a = new Fraction(2, 7);
        Fraction b = new Fraction(3, 11);
        Fraction c = new Fraction(5, 13);

        Fraction result = a.multiply(b, c);

        validate((result.getNumerator() == 2*3*5) && (result.getDenominator() == 7*11*13));
    }

    public static void division_is_correct(){
        System.out.print("Testing: division is correct... ");

        Fraction f = new Fraction(5, 4);
        Fraction divisor = new Fraction(3, 5);

        Fraction result = f.divide(divisor);

        validate((result.getNumerator() == 5*5) && (result.getDenominator() == 3*4));
    }

    public static void division_by_0_is_invalid(){
        System.out.print("Testing: division by zero should throw an ArithmeticException... ");

        Fraction f = new Fraction(1,1);
        Fraction f1 = new Fraction(0, 1);

        boolean success = true;
        try {
            // should throw an exception.
            f.divide(f1);
            success = false;
        } catch(ArithmeticException ignored){

        }
        validate(success);
    }

    public static void validate_toString(){
        System.out.print("Testing: validating toString method... ");

        Fraction f = new Fraction(1, 42);

        validate(f.toString().equals("1/42"));
    }

    public static void test_all(){
        denominator_should_Be_1_when_initialized_with_one_parameter();
        fraction_was_reduced_after_initialization();
        multiplication_with_factor_is_correct();
        multiplication_with_fraction_is_correct();
        multiplication_with_multiple_fractions_is_correct();
        division_is_correct();
        validate_toString();
        division_by_0_is_invalid();
    }

    public static void main(String[] args){
        TestFraction.test_all();
    }
}
