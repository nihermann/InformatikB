package Threading;

import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer(false); // isDaemon?
        timer.schedule(new MyTimerTask(), 100, 1000);

        Thread runnerThread = new Thread(new MyRunner());
        runnerThread.start();

        Thread thread = new MyThread();
        thread.start();
        
        Thread quickThread = new Thread(() -> {

        });
        quickThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

        }));
    }
}
