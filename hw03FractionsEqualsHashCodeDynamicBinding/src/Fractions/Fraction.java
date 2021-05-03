package Fractions;

import org.jetbrains.annotations.NotNull;

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
    * Numerator of the Fraction
    */
   private final int numerator;
   
   /**
    * Denominator of the Fraction
    */
   private final int denominator;

   /**
    * Creates a Fraction object with numerator and denominator 1,
    *
    * @param numerator
    */
   public Fraction(int numerator) {
      this(numerator, 1, false);
   }

   /**
    * Creates a Fraction object with numerator and denominator. Reduces the fraction in the constructor.
    * Denominator == 0 is not allowed. In such a case, the program terminates
    * with an error message.
    * @param numerator
    * @param denominator
    */
   public Fraction(int numerator, int denominator){
      this(numerator, denominator, true);
   }

   /**
    * Creates a Fraction object with numerator and denominator. Reduces the 
    * fraction in the constructor. 
    * Denominator == 0 is not allowed. In such a case, the program terminates
    * with an error message
    *
    * @param numerator   the numerator
    * @param denominator the denominator, must not be 0.
    * @param reduce      whether the fraction should get reduced or not.
    */
   public Fraction(int numerator, int denominator, boolean reduce) {
      if (denominator == 0) {
          System.out.println("denominator == 0 is not possible");
          System.out.println("Terminating program");
          System.exit(-1);
      }

      /*
       * creates greatest common divisor.
       */
      int gcd = 1;
      if(reduce) {
         gcd = Fraction.gcd(numerator, denominator);
      }
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
    * Adds two Fractions, denominators do not need to match.
    * @param summand The other Fraction to add with.
    * @return the resulting reduced Fraction.
    */
   public Fraction add(Fraction summand){
      Fraction first = this;
      if (first.denominator != summand.denominator){
         // if both fractions do not share a common denominator we expand both by multiplying with the opposing denominator/denominator.
         first = first.multiply(new Fraction(summand.denominator, summand.denominator, false));
         // important, use this because first would no longer have the same denominator.
         summand = summand.multiply(new Fraction(this.denominator, this.denominator, false));
      }

      return new Fraction(first.numerator + summand.numerator, first.denominator + summand.denominator);
   }

   /**
    * Subtracts two Fraction, denominators do not need to match.
    * @param subtrahend the other Fraction to subtract with.
    * @return the resulting reduced Fraction.
    */
   public Fraction subtract(Fraction subtrahend){
      Fraction first = this;
      if (first.denominator != subtrahend.denominator){
         // if both fractions do not share a common denominator we expand both by multiplying with the opposing denominator/denominator.
         first = first.multiply(new Fraction(subtrahend.denominator, subtrahend.denominator, false));
         // important, use this because first would no longer have the same denominator.
         subtrahend = subtrahend.multiply(new Fraction(this.denominator, this.denominator, false));
      }

      return new Fraction(first.numerator - subtrahend.numerator, first.denominator + subtrahend.denominator);
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

   /**
    * Parses a Fraction from a String. The String must be of the form: "-?\d+/\d+"
    * @param fraction the string representation of the String to be parsed.
    * @return the parsed Fraction.
    * @throws Exception if the String does not match the required form.
    */
   public static Fraction parseFraction(String fraction) throws Exception {
      if(!fraction.matches("-?\\d+/\\d+")){
         throw new Exception("The passed fraction string has an invalid form. It must be of the form: '[-]\\d+/\\d+'");
      }

      String[] parts = fraction.split("/");

      int numerator = Integer.parseInt(parts[0]);
      int denominator = Integer.parseInt(parts[1]);

      return new Fraction(numerator, denominator);
   }

}
