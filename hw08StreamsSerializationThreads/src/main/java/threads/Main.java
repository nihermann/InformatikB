package threads;

import java.io.File;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1){
            throw new IllegalArgumentException("Invalid amounts of arguments, expected one, received" + args.length);
        }
        File file = new File(args[0]);

        Timer t = new Timer();
        t.schedule(new CheckSize(file), 0, 1000);

        // add good bye shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Good Night Master")));
    }
}
