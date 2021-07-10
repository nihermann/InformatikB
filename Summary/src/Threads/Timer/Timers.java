package Threads.Timer;

import java.util.Timer;
import java.util.TimerTask;

public class Timers {


    public static void main(String[] args) {

        Timer timer = new Timer();


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer");
            }
        };

        // parameters: what to do (instance of timertask) when to begin and every x milliseconds
        timer.schedule(timerTask, 5000 , 1000);

    }
}
