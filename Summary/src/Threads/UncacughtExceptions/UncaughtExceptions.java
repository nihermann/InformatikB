package Threads.UncacughtExceptions;
import java.lang.Thread.UncaughtExceptionHandler;


public class UncaughtExceptions {

    public static void main(String[] args) {
        Thread exceptionsThrower = new Thread(){
            @Override
            public void run(){
                System.out.println("Start thread");
                throw new RuntimeException("Exception was thrown");
            }
        };

        UncaughtExceptionHandler uncaughtExceptionsHandler = new UncaughtExceptionHandler(){

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Exceptions were caught");
            }
        };

        exceptionsThrower.setUncaughtExceptionHandler(uncaughtExceptionsHandler);

        exceptionsThrower.start();

    }
}
