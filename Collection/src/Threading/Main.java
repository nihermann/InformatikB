package Threading;

import java.util.Timer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
    private Lock rLock = rwlock.readLock();
    private Lock wLock = rwlock.writeLock();

    private Condition rCondition = rLock.newCondition();

    private Semaphore semaphore = new Semaphore(2, true); // entrance is appr. even distributed for all threads.

    public synchronized void syncedMethod(){

    }

    public void monitorSyncedMethod(){
        synchronized(this){

        }
    }

    public void lockedMethod(){
        rLock.lock();
        wLock.lock();

        try{
            rCondition.await();



            rCondition.signal();
            rCondition.signalAll();
        } catch (Exception e){

        }finally{
            rLock.unlock();
            wLock.unlock();
        }
    }

    public void semaphoredMethod(){
        try{
            semaphore.acquire();
            

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }


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
