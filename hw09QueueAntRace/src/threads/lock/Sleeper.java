package threads.lock;

import util.LockedQueue;
import util.Queue;

/**
 * Simple {@code Thread} which continuously reads random values from the given
 * {@code Queue} and sleeps for as long as the currently read value determines.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 */
public class Sleeper extends Thread {

   private LockedQueue<Long> values;

   public Sleeper(LockedQueue<Long> values) {
      this.values = values;
   }

   public void run() {
      try {
         while (true) {
            long value;

            value = values.deq();
            System.out.println("Now sleeping for " + value + " ms");

            this.sleep(value);

         }
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
}
