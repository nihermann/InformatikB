package Synchronisation.ProducerConsumerLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Buffer  {

    private int max;
    private int current;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    private Condition condition = readWriteLock.writeLock().newCondition();

    public Buffer(int max){
        this.max = max;
        this.current = 0;
    }

    public int getMax() {
        return max;
    }

    public int getCurrent() {
        return current;
    }

    public void put(){
        try{
            writeLock.lock();
            while (this.current >= this.max){
                condition.await();
            }
            this.current++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            condition.signalAll();
            writeLock.unlock();
        }
    }

    public void take(){
        try {
            writeLock.lock();
            while (this.current <= 0){
                condition.await();
            }
            this.current--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            condition.signalAll();
            writeLock.unlock();
        }

    }

}


