/**
 *
 */
public class Fraction {
    /**
     *
     */
    private int numerator;
    /**
     *
     */
    private int denominator;

    /**
     *
     * @param numerator
     * @param denominator
     * @throws ArithmeticException
     */
    public Fraction(int numerator, int denominator) throws ArithmeticException{
        if (denominator == 0 && numerator != 0){
            throw new ArithmeticException("Division by 0 is invalid!");
        }
        reduce(numerator, denominator);
    }

    /**
     *
     * @param numerator
     */
    public Fraction(int numerator){
        this(numerator, 1);
    }

    /**
     *
     * @param numerator
     * @param denominator
     */
    private void reduce(int numerator, int denominator) {
        if (numerator == denominator){
            this.numerator = 1;
            this.denominator = 1;
        }
        for (int i = denominator/2; 1 < i; i--){
            if ((numerator % i == 0) && (denominator % i == 0)){
                numerator /= i;
                denominator /= i;
            }
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     *
     * @param factor
     * @return
     */
    public Fraction multiply(int factor){
        return new Fraction(this.numerator * factor, this.denominator);
    }

    /**
     *
     * @param factor
     * @return
     */
    public Fraction multiply(Fraction factor){
        return new Fraction(
                this.numerator * factor.getNumerator(),
                this.denominator * factor.getDenominator()
        );
    }

    /**
     *
     * @param factors
     * @return
     */
    public Fraction multiply(Fraction... factors){
        Fraction result = this;
        for (Fraction f: factors){
            result = result.multiply(f);
        }
        return result;
    }

    /**
     *
     * @param divisor
     * @return
     */
    public Fraction divide(Fraction divisor){
        Fraction inverse_fraction = new Fraction(divisor.getDenominator(), divisor.getNumerator());
        return this.multiply(inverse_fraction);
    }

    /**
     *
     * @return
     */
    public String toString(){
        return (this.numerator + "/" + this.denominator);
    }

    /**
     *
     * @return
     */
    public int getNumerator(){
        return this.numerator;
    }

    /**
     *
     * @return
     */
    public int getDenominator(){
        return this.denominator;
    }
}
