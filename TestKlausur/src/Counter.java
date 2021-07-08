public class Counter extends Thread {

         private static Integer a = 0;

         public void run() {
             for (int i = 0; i < 100; i++) {
                 synchronized(Counter.class) {
                     a++;
                     System.out.println(a);
                 }
             }
         }


         public static void main(String[] args){
             for (int i = 0; i < 10; i++) {
                 Counter c = new Counter();
                 c.start();
//                 try {
//                     c.join();
//                 } catch (InterruptedException e) {
//                     e.printStackTrace();
//                 }
             }

         }
 }

