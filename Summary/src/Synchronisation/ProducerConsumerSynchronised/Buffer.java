package Synchronisation.ProducerConsumerSynchronised;

public class Buffer  {

    private int max;
    private int current;

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

    public synchronized void put(){
        while (this.current >= this.max){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.current++;
        this.notifyAll();
    }

    public synchronized void take(){
        while (this.current <= 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }        }
        this.current--;
        this.notifyAll();
    }

}


