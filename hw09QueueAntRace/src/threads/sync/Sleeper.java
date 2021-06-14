package threads.sync;

import util.Queue;

/**
 * Simple {@code Thread} which continuously reads random values from the given
 * {@code Queue} and sleeps for as long as the currently read value determines.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 */
public class Sleeper extends Thread {

   private Queue<Long> values;

   public Sleeper(Queue<Long> values) {
      this.values = values;
   }

   public void run() {
      try {
         synchronized (this.values) {
            while (true) {
               long value;
               while(this.values.empty()){
                  this.values.wait();
               }
               value = values.deq();
               this.values.notifyAll();

               System.out.println("Now sleeping for " + value + " ms");
               this.sleep(value);
            }
         }
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
}
