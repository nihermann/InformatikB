package Threads.Demoens;

public class Demoen {
    public static void main(String[] args) {
        Thread demoen = new Thread(){
            @Override
            public void run(){
                while (true){
                    System.out.println("Demoen");
                }
            }
        };

        demoen.setDaemon(true);
        demoen.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
