package Synchronisation.ProducerConsumerSynchronised;

public class Producer extends Thread{

    private Buffer buffer;

    public Producer(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        for( int i = 0; i <= 50; i++){
            buffer.put();
            System.out.println("Producer put.");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
