package Altklausur.model.timer;

import javax.swing.*;
import java.util.TimerTask;

public class BreakTimerTask extends TimerTask {
    @Override
    public void run() {
        JOptionPane.showMessageDialog(null,"Break time!");
    }
}
