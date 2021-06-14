
import aufgabe.io.FileThread;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class TestFileThread {
    public static void main(String[] args) {
        if (args.length != 1){
            throw new IllegalArgumentException("Invalid amounts of arguments, expected one, received" + args.length);
        }
        File file = new File(args[0]);

        Timer t = new Timer();
        FileThread fileStream = new FileThread(file);
        t.schedule(fileStream, 0, 1000);

        // add good bye shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("The program was stopped the last measured size is: "+fileStream.getSize())));


    }
}
