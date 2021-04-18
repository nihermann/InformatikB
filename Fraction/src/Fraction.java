/**
 *
 */
public class Fraction {
    /**
     * an integer to store the value of the numerator of the fraction.
     */
    private int numerator;
    /**
     * an integer to store the value of the denominator of the fraction.
     */
    private int denominator;

    /**
     * Crates an instance of a reduced fraction. The denominator must not be 0.
     * @param numerator: int - the numerator of the fraction.
     * @param denominator: int - the denominator of the fraction (non 0)
     * @throws ArithmeticException: throws an exception if the denominator is 0. (Division by 0)
     */
    public Fraction(int numerator, int denominator) throws ArithmeticException{
        // if the denominator is 0 we would try to divide by 0 which is undefined/invalid.
        if (denominator == 0){
            throw new ArithmeticException("Division by 0 is invalid!");
        }
        // reduces the fraction.
        reduce(numerator, denominator);
    }

    /**
     * Transforms an integer to a fraction. (e.g. 2/1, 16/1, ...)
     * @param numerator: int - value of the numerator.
     */
    public Fraction(int numerator){
        this(numerator, 1);
    }

    /**
     * Reduces a fraction.
     * @param numerator: int - value of the numerator.
     * @param denominator: int - value of the denominator.
     */
    private void reduce(int numerator, int denominator) {
        // if num and denom are identical the value is always 1 (1/1).
        if (numerator == denominator){
            this.numerator = 1;
            this.denominator = 1;
        }

        for (int i = (denominator/2) + 1; 1 < i; i--){
            if ((numerator % i == 0) && (denominator % i == 0)){
                numerator /= i;
                denominator /= i;
            }
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Multiply the fraction by a factor.
     * @param factor: int - the multiplicative factor.
     * @return the resulting fraction.
     */
    public Fraction multiply(int factor){
        return new Fraction(this.numerator * factor, this.denominator);
    }

    /**
     * Multiply a fraction with another fraction.
     * @param factor: Fraction - the multiplicative factor (Fraction).
     * @return the resulting fraction.
     */
    public Fraction multiply(Fraction factor){
        return new Fraction(
                this.numerator * factor.getNumerator(),
                this.denominator * factor.getDenominator()
        );
    }

    /**
     * Multiply multiple fractions.
     * @param factors: Fractions - multiple fractions for multiplication.
     * @return the resulting fraction.
     */
    public Fraction multiply(Fraction... factors){
        Fraction result = this;
        for (Fraction f: factors){
            result = result.multiply(f);
        }
        return result;
    }

    /**
     * Divide the fraction by another fraction.
     * @param divisor: Fraction - the fraction to divide with.
     * @return the resulting fraction.
     * @throws ArithmeticException: throws an exception if the denominator is 0. (Division by 0)
     */
    public Fraction divide(Fraction divisor) throws ArithmeticException {
        // obtain the inverse fraction (Kehrbruch) and multiply it with this.
        Fraction inverse_fraction = new Fraction(divisor.getDenominator(), divisor.getNumerator());
        return this.multiply(inverse_fraction);
    }

    /**
     * Overrides the method toString.
     * @return the String representation of the fraction.
     */
    public String toString(){
        return (this.numerator + "/" + this.denominator);
    }

    /**
     * Getter for the Numerator.
     * @return the value of the numerator.
     */
    public int getNumerator(){
        return this.numerator;
    }

    /**
     * Getter for the Denominator.
     * @return the value of the denominator.
     */
    public int getDenominator(){
        return this.denominator;
    }
}
