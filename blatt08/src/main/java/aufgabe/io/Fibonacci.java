package aufgabe.io;

import java.io.*;
import java.util.HashMap;

/**
 * Class to calculate the Fibonacci number. Uses a HashMap to reduce the
 * calculation cost.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Fibonacci{

   private static HashMap<Integer, Long> fibonacciHash;
   private static String filename = "Fibonacci.ser";

   /**
    * Calculates the fibonacci value of n.
    *
    * @param n a natural number >= 0 to calculate the fibonacci value of
    * @return fibonacci value of n
    *
    * Additionally loads and writes the fibonacci hash into a file to retrieve later
    */
   public static long fibonacci(int n) throws IOException, ClassNotFoundException {
      if (n < 0) {
         throw new IllegalArgumentException("n = " + n);
      }


      if(fibonacciHash == null){
         File file = new File(filename);

         if(!file.exists()){
            // create only a new Hashmap if none has been already saved
            fibonacciHash = new HashMap<>();
            fibonacciHash.put(0, 0L);
            fibonacciHash.put(1, 1L);
         }else{
            // if a Hashmap was previously saved load it into the current instance
            try(ObjectInputStream loading = new ObjectInputStream(new FileInputStream(file))) {
               fibonacciHash = (HashMap<Integer, Long>) loading.readObject();
            }
         }
      }

      // save the current size and the new fibonacci so you only update/write the file if needed
      int saved = fibonacciHash.size();
      long fibonacci = getFibonacci(n);

      // only save the new fibonacciHash if new values have been added
      if(fibonacciHash.size() != saved){
         // write the new fibonacciHash in the file via ObjectOutputStream
         try(ObjectOutputStream saving = new ObjectOutputStream(new FileOutputStream(filename))) {
            saving.writeObject(fibonacciHash);
            // norm is to call flush after calling an output stream but as we use the autoclausable syntax we do not
            // need it as it is the default
         }
      }

      // return the fibonacci value
      return fibonacci;
   }

   private static long getFibonacci(int n) {
      if (fibonacciHash.containsKey(n)) {
         return fibonacciHash.get(n);
      } else {
         long nMinus1 = getFibonacci(n - 1);
         long nMinus2 = getFibonacci(n - 2);
         long fibonacci = nMinus1 + nMinus2;

         fibonacciHash.put(n, fibonacci);
         return fibonacci;
      }
   }

   public static void main(String[] args) throws IOException, ClassNotFoundException {
      if (args.length != 1) {
         printUsage();
      } else {
         try {
            System.out.println(fibonacci(Integer.parseInt(args[0])));

            System.out.println("This is my fibonacciHash: " + fibonacciHash.toString());

         } catch (IllegalArgumentException ex) {
            printUsage();
         }
      }
   }

   private static void printUsage() {
      System.out.println("java calc/Fiboncci n");
      System.out.println("n must be a natural number >= 0");
   }

}
