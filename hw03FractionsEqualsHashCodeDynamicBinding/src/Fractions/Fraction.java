/**
 * Every instance of <code>Fraction</code> represents a fraction with numerator
 * and denominator.
 *
 * @author Lars Huning
 */
public class Fraction {

   /**
    * Creates greatest common divisor for a and b.
    *
    * @param a
    * @param b
    * @return the greatest common divisor for a and b.
    */
   public static int gcd(int a, int b) {
      return b == 0 ? a : gcd(b, a % b);
   }

   /**
    * Creates greatest common divisor for a and b.
    *
    * @param a
    * @param b
    * @return the least common multiplier for a and b.
    */
   static int lcm(int a, int b) { return (a / gcd(a, b)) * b; }

   /**
    * Numerator of the Fraction
    */
   private final int numerator;

   /**
    * Denominator of the Fraction
    */
   private final int denominator;

   /**
    * Creates a Fraction object with numerator and denominator 1, reduces the
    * fraction with creation.
    *
    * @param numerator
    */
   public Fraction(int numerator) {
      this(numerator, 1);
   }

   /**
    * Creates a Fraction object with numerator and denominator. Reduces the
    * fraction in the constructor.
    * Denominator == 0 is not allowed. In such a case, the program terminates
    * with an error message
    *
    * @param numerator   the numerator
    * @param denominator the denominator, must not be 0.
    */
   public Fraction(int numerator, int denominator) {
      if (denominator == 0) {
          System.out.println("denominator == 0 is not possible");
          System.out.println("Terminating program");
          System.exit(-1);
      }

      /*
       * creates greatest common divisior.
       */
      int gcd = Fraction.gcd(numerator, denominator);

      /*
       * check sign, make denominator positive --> the sign of the fraction
       * is always stored only in the numerator.
       */
      if (denominator / gcd < 0) {
         gcd *= -1;
      }

      this.numerator = numerator / gcd;

      this.denominator = denominator / gcd;
   }

   /**
    * Divides this Fraction with another Fraction. Terminates the program in
    * case numerator of another is zero (via constructor of the newly created
    * Fraction).
    *
    * @param another Fraction to divide this Fraction by
    * @return Quotient as a new Fraction
    *
    */
   public Fraction divide(Fraction another) {
      return new Fraction(this.numerator * another.denominator,
            this.denominator * another.numerator);
   }

   /**
    * Divides this Fraction with another Fraction. Terminates the program in
    * case numerator of another is zero (via constructor of the newly created
    * Fraction).
    *
    * @param addend Fraction to add
    * @return Sum as a new Fraction
    *
    */
   public Fraction add(Fraction addend){
//      multiply the least common multiplier with both summands and then add their numerator and the denominator
      int lcm = lcm(this.denominator, addend.denominator);
      Fraction augend = this.multiply(lcm);
      addend = addend.multiply(lcm);



      return new Fraction(augend.getNumerator()+addend.getNumerator(), lcm);
   }

   /**
    * Divides this Fraction with another Fraction. Terminates the program in
    * case numerator of another is zero (via constructor of the newly created
    * Fraction).
    *
    * @param subtrahend Fraction to substract by
    * @return Sum as a new Fraction
    *
    */
   public Fraction substract(Fraction subtrahend){
      return this.add(subtrahend.multiply(-1));
   }


   /**
    * Note: "Default" getters and setters do not always require JavaDoc,
    * as their purpose is obvious
    */
   public int getDenominator() {
      return this.denominator;
   }

   public int getNumerator() {
      return this.numerator;
   }

   /**
    * Multiplies this Fraction with another Fraction
    *
    * @param factor Fraction to multiply this Fraction with
    * @return The product as a new Fraction
    */
   public Fraction multiply(Fraction factor) {
      return new Fraction(this.numerator * factor.numerator, this.denominator
            * factor.denominator);
   }

   /**
    * Multiplies this Fraction with all other Fraction instances given by
    * the parameter factors
    *
    * @param factors Fraction instances to multiply this Fraction with
    * @return The product as a new Fraction
    */
   public Fraction multiply(Fraction... factors) {
      Fraction result = this;

      //variable parameters may be treated like an array inside the method
      for (int i = 0; i < factors.length; i++) {
         result = result.multiply(factors[i]);
      }
      return result;
   }

   /**
    * Multiplies this Fraction with int n.
    *
    * @param n factor to multiply with
    * @return The product as a new Fraction
    */
   public Fraction multiply(int n) {
      return new Fraction(this.numerator * n, this.denominator);
   }

   /**
    * Returns a string representation of this Fraction such as
    * numerator/denominator. As the constructor has already made sure that
    * a negative Fraction is represented by a negative numerator and a positive
    * denominator, negative fractions are always displayed with the minus sign
    * at the numerator (Advantage: consistent notation, for example if anyone
    * decides to parse the results of this method with a string parser)
    *
    * @return This Fraction as a String
    */
   public String toString() {
      return numerator + "/" + denominator;
   }


   public static Fraction parseFraction(String stringFraction) throws Exception{
      String numerator_pattern = "-?(0|\\d+)";
      String denominator_pattern = "[1-9]\\d*";
      String fraction_pattern = numerator_pattern+"/"+denominator_pattern;

      if (stringFraction.matches(fraction_pattern)){
         String[] fraction= stringFraction.split("/");
         try{
            int numerator = Integer.parseInt(fraction[0]);
            int denominator = Integer.parseInt(fraction[1]);
            return new Fraction(numerator,denominator);
         }catch (NumberFormatException e){
            System.out.println("Error:" + e + "Meaning the string you entered could not be parsed into an Integer.");
         }
      }
      throw new Exception("The string has to be put into valid format (integer/integer).");
   }

}
