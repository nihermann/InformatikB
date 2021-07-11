package Altklausur.model.timer;

import java.util.Timer;
import java.util.TimerTask;

public class BreakAlarm extends Timer {

    private final int interval = 35;

    public void begin(){
        Timer timer = new Timer(true);

        TimerTask breakTimerTask = new BreakTimerTask();
        timer.schedule(breakTimerTask,interval*1000,interval*1000);
    }

}
