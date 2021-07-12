package Threads;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        Thread threadRunnable = new Thread(new MyRunnable());
        threadRunnable.start();

        Thread annonymousThread = new Thread(){
            @Override
            public void run(){
                while (true){
                    System.out.println("3");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("3 Wurde wieder aufgeweckt");
                    }

                }
            }
        };
        annonymousThread.start();

        Thread.sleep(1500);

        myThread.interrupt();
        threadRunnable.interrupt();
        annonymousThread.interrupt();
    }
}
