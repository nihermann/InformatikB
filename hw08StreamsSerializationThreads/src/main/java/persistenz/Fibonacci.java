package persistenz;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.HashMap;

/**
 * Class to calculate the Fibonacci number. Uses a HashMap to reduce the
 * calculation cost.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Fibonacci {

   private final static HashMap<Integer, Long> fibonacciHash;

   /**
    * Fill HashMap with initial key-value-pairs.
    */
   static {
      fibonacciHash = new HashMap<>();
      fibonacciHash.put(0, 0L);
      fibonacciHash.put(1, 1L);
   }

   /**
    * Calculates the fibonacci value of n.
    *
    * @param n a natural number >= 0 to calculate the fibonacci value of
    * @return fibonacci value of n
    */
   public static long fibonacci(int n) {
      File results = new File("src/main/java/persistenz/fib.xml");
      if (results.exists()) {
         read(results);
      }
      if (n < 0) {
         throw new IllegalArgumentException("n = " + n);
      }
      long result = getFibonacci(n);
      serialize(results);
      return result;
   }

   /**
    * Serializes all key-value pairs from the fibonacciHash map.
    * @param fib file to serialize to.
    */
   private static void serialize(File fib) {
      try(FileOutputStream dst = new FileOutputStream(fib); XMLEncoder enc = new XMLEncoder(dst)){
         enc.writeObject(fibonacciHash);
      } catch(IOException e){
         e.printStackTrace();
      }
   }

   /**
    * Reads all serialized results from the stored hashmap.
    * @param file where results are serialized.
    */
   private static void read(File file) {
      try (FileInputStream src = new FileInputStream(file); XMLDecoder dec = new XMLDecoder(src)) {
         // read the HashMap and merge it with the static final one.
         HashMap<Integer, Long> hm = (HashMap<Integer, Long>) dec.readObject();
         fibonacciHash.putAll(hm);
      } catch (IOException e) {
         e.printStackTrace();
      }
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

   public static void main(String[] args) {
      if (args.length != 1) {
         printUsage();
      } else {
         try {
            System.out.println(fibonacci(Integer.parseInt(args[0])));

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
