package model.timer;

import java.util.Timer;

public class BreakAlarm {
    private final int interval = 64;
    public void begin(){
        Timer t = new Timer(true);
        t.schedule(new BreakTimerTask(), interval*1000, interval*1000);
    }
}
