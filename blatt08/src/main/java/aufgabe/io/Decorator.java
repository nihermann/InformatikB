package aufgabe.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Decorator {
    public static void main(String[] args) {
        // Streams:
        // Streams are pipelines which object can be passed through while in that stream operations can
        // be applied to the given object these include but are not limited to mapping, filtering or collecting.
        // There are used for data transferring.

        // The System.out stream is a (Output) Print stream displaying the input on the console
        System.out.println("This is an output.");

        // Similar to System.out System.err is also a (Output) PrintStream typically used in a catch clause after an error occurs
        // Some IDE's print the input of System.err in red to symbolise its warning characteristic
        System.err.println("This is an error output.");

        // System.in is an Input stream that can take console input from the user
        // BUffered Reader extends Reader and implements the method readLine thereby being able to
        // read complete lines of input
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        try {
            String enteredInput = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // The Decorator design pattern implements the idea of adding functionalities while transferring data.
        // The condreateDecorator extends the decorator and calls its super method while also adding behavior or states.


    }
}
