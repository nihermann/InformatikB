package Synchronisation.ProducerConsumerSynchronised;

public class Consumer extends Thread{

    private Buffer buffer;

    public Consumer(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        for( int i = 0; i <= 50; i++){


            buffer.take();
            System.out.println("Consumer took.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
