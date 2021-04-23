/**
 *
 */
package Test;

public class Test {
    /**
     *
     */
    private static float precision = 0.001f;
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
     * @param is
     * @param should
     * @param errorMessage
     */
    public static void shouldApproximatelyBe(double is, double should, String errorMessage) {
        if (Math.abs(should - is) > precision) {
            System.out.println(errorMessage);
            errorCount++;
        }
    }

    /**
     * @param is
     * @param should
     * @param errorMessage
     */
    public static void shouldApproximatelyBe(float is, float should, String errorMessage) {
        if (Math.abs(should - is) > precision) {
            System.out.println(errorMessage);
            errorCount++;
        }
    }

    public static int getErrorCount(){
        return errorCount;
    }
}

