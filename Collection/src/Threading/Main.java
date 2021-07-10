package Threading;

import java.util.Timer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // timer task
        Timer timer = new Timer(false); // isDaemon?
        timer.schedule(new MyTimerTask(), 100, 1000);

        // with runnable
        Thread runnerThread = new Thread(new MyRunner());
        runnerThread.start();

        // as thread
        Thread thread = new MyThread();

//        Thread.setDefaultUncaughtExceptionHandler((thrd, ex) -> System.out.println(ex.getMessage()));
//        thread.setUncaughtExceptionHandler((thrd, ex) -> System.out.println(ex.getMessage()));

        thread.start();

        // lambda
        Thread quickThread = new Thread(() -> {

        });
        quickThread.start();

        // add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

        }));

        // executor
        var executor = Executors.newCachedThreadPool();
        executor.execute(new MyRunner());
        executor.execute(new MyRunner());

        // executor will not accept new runnables.
        executor.shutdown();

    }
}
