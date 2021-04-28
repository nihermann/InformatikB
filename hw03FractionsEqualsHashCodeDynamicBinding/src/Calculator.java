/**
 * To use this calculator use it as follows:
 *
 * $ java Calculator Fraction "Operand" Fraction
 *
 * where   Fraction ::= [-]Number/Number
 * and     Operand  ::= [+][-][*][/]
 *
 * @Author Nicolai Hermann, Michael Hüppe
 */

import Fractions.Fraction;

public class Calculator {

    public static void main(String[] args) throws Exception {
        checkValidity(args);

        Fraction f1 = Fraction.parseFraction(args[0]);
        Fraction f2 = Fraction.parseFraction(args[2]);

        Fraction result = getResult(args[1], f1, f2);
        System.out.println(args[0] + " " + args[1] + " " + args[2] + " = " + result);
    }

    /**
     * Performs an arithmetic operation on two fractions.
     * @param operand String - the operand, either *, /, +, -
     * @param f1 Fraction number 1.
     * @param f2 Fraction number 2.
     * @return the result of the calculation.
     */
    private static Fraction getResult(String operand, Fraction f1, Fraction f2) {
        return switch (operand) {
            case "*" -> f1.multiply(f2);
            case "/" -> f1.divide(f2);
            case "-" -> f1.subtract(f2);
            default -> f1.add(f2);
        };
    }

    /**
     * Checks the input arguments for validity.
     * @param input supplied arguments.
     * @throws Exception if input has invalid format.
     */
    private static void checkValidity(String[] input) throws Exception {
        String manual =
                """
                To use this calculator use it as follows:
                
                $ java Calculator Fraction "Operand" Fraction
                
                where   Fraction ::= [-]Number/Number
                and     Operand  ::= [+][-][*][/]
                """;

        String validFraction = "-?\\d+/\\d+";
        String validOperand = "[-*+/]";

        if (input.length != 3){
            System.out.println(manual);
            throw new Exception("Expected 3 arguments, revived: " + input.length);
        }
        
        for ( int i = 0; i <= 2; i += 2){ // Check if the Fractions are valid.
            if (!input[i].matches(validFraction)){
                System.out.println(manual);
                throw new Exception("Invalid Fraction form, expected form " + validFraction + ", found: " + input[i]);
            }
        }
        
        if (!input[1].matches(validOperand)){ // Check if the operand is valid.
            System.out.println(manual);
            throw new Exception("Invalid operand, expected one of \"*\", \"/\", \"+\", \"-\", found: " + input[1]);
        }
    }
}
