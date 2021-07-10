package Threads;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("2");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("2 Wurde wieder aufgeweckt");
            }

        }
    }
}
