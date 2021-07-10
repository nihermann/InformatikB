package Threads;

public class MyThread extends Thread{

    @Override
    public void run(){
        while (true){
            System.out.println("1");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("1 Wurde wieder aufgeweckt");
            }

        }
    }
}
