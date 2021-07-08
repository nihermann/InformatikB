package model.timer;

import javax.swing.*;
import java.util.TimerTask;

public class BreakTimerTask extends TimerTask {
    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        JOptionPane.showMessageDialog(null, "Break Time!");
    }
}
