package Threads.ShutdownHooks;

public class ShutDownHooks {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                System.out.println("Die main wurde beendet und der shutdown hook wird aktiviert");
            }
        });

        while (true){

        }

        // muss über exit beendet werden (alternative des ctrl c shortcuts in intellij)
    }
}
