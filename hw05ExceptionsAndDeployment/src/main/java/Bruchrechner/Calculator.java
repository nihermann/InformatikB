package Bruchrechner;

/**
 * Contains a main class to execute a Calculate instance, which calculates an
 * operation on two Fraction instances.
 *
 * @author Lars Huning
 */
public class Calculator {

   /*
    * The Operators
    */
   public static final String ADD = "+";
   public static final String SUBTRACT = "-";
   public static final String MULTIPLY = "*";
   public static final String DIVIDE = "/";

   /**
    * @param args
    */
   public static void main(String[] args) {

      if (args.length != 3) {
         System.err.println("invalid number of arguments (must be three)");
         printUsage();
         System.exit(1);
      }

      Calculator calc = new Calculator();
      String result = calc.calc(args[0], args[1], args[2]);

      if (result == null) {
         System.err.println(calc.getErrorMessage());
         printUsage();
         System.exit(1);
      }

      System.out.println("= " + result);


   }

   /**
    * Prints a short description of the usage on the standard console.
    */
   private static void printUsage() {
      System.out.println("Usage: java Calculator fraction operator fraction");
      System.out.println("a fraction is defined by " + Fraction.REGEX);
      System.out.println("valid operators are +,-, *, /");
      System.out.println("fractions may also be replaced by simple numerical values such as 2 or 1.7243");
   }

   /**
    * Holds the error message of the last call of
    * {@link #calc(String, String, String)} which went wrong.
    */
   private String errorMessage;

   /**
    * Calculates the formula given by <code>a operator b</code>. If a, operator
    * or b are not valid, null will be returned and errorMessage will hold a
    * description of the error that occurred.
    *
    * @param a        the first Fraction argument
    * @param operator operator to connect the arguments with
    * @param b        the second Fraction argument
    * @return The result of the operation as Fraction or null.
    */
   private Fraction calc(Fraction a, String operator, Fraction b) {

      Fraction result;
      /*
       * differentiate between operators and compute regarding operation.
       */
      switch (operator) {

         case ADD:
            result = a.add(b);
            break;
         case SUBTRACT:
            result = a.subtract(b);
            break;
         case MULTIPLY:
            result = a.multiply(b);
            break;
         case DIVIDE:
            if (b.getNumerator() == 0) {
               this.errorMessage = "divides zero";
               return null;
            } else {
               result = a.divide(b);
            }
            break;
         default:
            this.errorMessage = "Operation " + operator + " unknown";
            return null;
      }

      return result;

   }

   /**
    * Calculates the formula given by <code>a operator b</code>. If a, operator
    * or b are not valid, null will be returned and errorMessage will hold a
    * description of the error that occurred.
    *
    * @param a        the first numerical argument
    * @param operator operator to connect the arguments with
    * @param b        the second numerical argument
    * @return The result of the operation as Float or null.
    */
   private Float calc(Float a, String operator, Float b){
      Float result;
      switch(operator){
         case ADD:      result = a + b; break;
         case SUBTRACT: result = a - b; break;
         case MULTIPLY: result = a * b; break;
         case DIVIDE:   result = (b == 0? null : a / b ); break;
         default:       result = null;
      }
      if ( result == null ){
         this.errorMessage = (b == 0? "Division by zero" : "Unknown operator, found: " + operator);
      }
      return result;
   }

   /**
    * Calculates the formula given by <code>a operator b</code>. If a, operator
    * or b are not valid, null will be returned and errorMessage will hold a
    * description of the error that occurred.
    *
    * @param a        String representation of the first argument
    * @param operator operator to connect the arguments with
    * @param b        String representation of the second argument
    * @return The result of the operation as String or null.
    */
   public String calc(String a, String operator, String b) {

      // if not both arguments are Fractions we will try to interpret them as numerical values.
      if(!a.matches(Fraction.REGEX) || !b.matches(Fraction.REGEX)){
         Float val_a = parseNumber(a);
         Float val_b = parseNumber(b);

         if ( val_a == null || val_b == null || operator == null){
            return null;
         }

         Float result = calc(val_a, operator, val_b);
         return (result == null? null : result.toString());

      }

      // if both arguments are convertible to Fractions we will do so.
      Fraction fractionA = parseFraction(a);
      Fraction fractionB = parseFraction(b);

      if (fractionA == null || fractionB == null || operator == null) {
         return null;
      }

      Fraction result = calc(fractionA, operator, fractionB);

      return result == null? null : result.toString();
   }

   /**
    * Return the error message of the last call of
    * {@link #calc(String, String, String)} which went wrong.
    *
    * @return the last error message
    */
   public String getErrorMessage() {
      return this.errorMessage;
   }

   /**
    * Parses the given String to a Float and returns it. If it cannot be
    * parsed, null will be returned and errorMessage will hold a description of
    * the error that occurred.
    *
    * @param numeric String to be parsed to a Float.
    * @return A Float representing the given String or null.
    */
   public Float parseNumber(String numeric){
      // Regex matching whole numbers or floating point numbers.
      String number = "-?\\d+(\\.\\d+)?";
      if ( numeric.matches(number) ){
         return Float.parseFloat(numeric);
      }

      // if it matches a Fraction signature we will parse it to one and extract its Float value afterwards.
      if( numeric.matches(Fraction.REGEX) ){
         return Fraction.parseFraction(numeric).floatValue();
      }

      // if conversion was not possible we will terminate.
      errorMessage = numeric + " is not a valid Fraction";
      return null;
   }

   /**
    * Parses the given String to a Fraction and returns it. If it cannot be
    * parsed, null will be returned and errorMessage will hold a description of
    * the error that occurred.
    *
    * @param fraction String to be parsed to a fraction
    * @return A Fraction representing the given String or null.
    */
   private Fraction parseFraction(String fraction) {
      if (!fraction.matches(Fraction.REGEX)) {
         errorMessage = fraction + " is not a valid Fraction";
         return null;
      }
      return Fraction.parseFraction(fraction);

   }

}
