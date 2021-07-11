package NebenlauefigkeitKlausur;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter extends Thread {
    private static Integer a = 0;
    private static int numerator = 0;
    private int identifier;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writeLock = readWriteLock.writeLock();

    public Counter(){
        identifier = numerator;
        this.numerator++;
    }

    @Override
    public void run(){
        for (int i = 0; i < 100; i++){
            synchronized (Counter.class){
                a++;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i ++){
            Counter c = new Counter();
            c.start();
        }
        // Die in der run methode des threads aufgerufenene Anweisung a++ ist nicht atomar, das heißt, sie kann
        // zwischen durch unterbrochen werden (also threads werden gewechselt). Das heißt Thread 1 kann sich den Wert der
        // static variable a holen und dann von Thread 2 unterbrochen werden.
        // Dieser greift auf ebenfalls den wert von a zu (den gleichen wie Thread 1) und incrementiert diesen
        // (angenommen er wird nicht ebenfalls von einem anderen Thread unterbrochen/ bzw vom schedular abgelöst).
        // Wenn dann Thread 1 jedoch wieder zum zuge kommt, arbeitet er mit dem Wert weiter den er sich zuvor geholt hat.
        // Das heißt die Inkrementation von Thread 2 wird nicht beachtet und Thread 1 überschreibt diese.
        // Dies ist auch als das Lost update problem bekannt (das verlorene update in dem beispiel ist die erhöhung von
        // Thread2).

        Thread.sleep(500);
        System.out.println("Das ende:" + Counter.a);

    }
}
