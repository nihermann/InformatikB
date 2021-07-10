package Synchronisation.ProducerConsumerLock;

public class Main {
    // Consumer Producer problem
    // Zwei nebenläufige Threads greifen auf den gleichen buffer zu (Speicherbereich / Depot).
    // Producer: erzeugt einheiten und legt sie in den buffer
    // Consumer: liest die einheiten aus dem buffer /verbraucht sie
    // Problem: Wenn der buffer voll ist muss der producer warten (darf/kann nichts mehr drauf tun)
    //          Wenn der buffer leer ist muss der consumer warten (darf/kann der nichts mehr vom buffer nehmen)

    public static void main(String[] args) {

        Buffer buffer = new Buffer(10);

        Producer producer = new Producer(buffer);

        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main finished");
    }
}
