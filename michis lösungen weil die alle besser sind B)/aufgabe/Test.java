/**
 * Basic functions for testing.
 * @Author Nicolai Hermann, Michael Hüppe
 */

public class Test {
    /**
     * Sets the precision for floating point value comparisons.
     */
    private static float precision = 0.001f;
    /**
     * Counts how often a test failed.
     */
    private static int errorCount = 0;

    /**
     * Sets epsilon/ precision for floating point comparisons.
     * @param precision - float must be smaller than 0.1
     */
    public static void setPrecision(float precision) {
        Test.precision = precision < 0.1 ? precision : Test.precision;
        errorCount++;
    }

    /**
     * If the passed bool is not true, the error message will be printed.
     * @param bool whether the test succeeded or not.
     * @param errorMessage the error message to print if test failed.
     */
    public static void shouldBeTrue(boolean bool, String errorMessage) {
        if (!bool) {
            System.out.println(errorMessage);
            errorCount++;
        }
    }

    /**
     * Checks if 'is' is approximately 'should'. How far they are allowed to deviate is set by the class variable 'precision'.
     * @param is double - the observed value.
     * @param should double - the value 'is' should have.
     * @param errorMessage String - the error message which should be printed if testing fails.
     */
    public static void shouldApproximatelyBe(double is, double should, String errorMessage) {
        if (Math.abs(should - is) > precision) {
            System.out.println(errorMessage);
            errorCount++;
        }
    }

    /**
     * Checks if 'is' is approximately 'should'. How far they are allowed to deviate is set by the class variable 'precision'.
     * @param is float - the observed value.
     * @param should float - the value 'is' should have.
     * @param errorMessage String - the error message which should be printed if testing fails.
     */
    public static void shouldApproximatelyBe(float is, float should, String errorMessage) {
        if (Math.abs(should - is) > precision) {
            System.out.println(errorMessage);
            errorCount++;
        }
    }

    /**
     * Returns how many tests failed so far.
     * @return number of errors while testing.
     */
    public static int getErrorCount(){
        return errorCount;
    }

    public static void printError(){
        System.out.println(errorCount);
    }
}

