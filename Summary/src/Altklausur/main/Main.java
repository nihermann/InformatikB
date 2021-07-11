package Altklausur.main;

import Altklausur.model.logic.CustomerSystem;
import Altklausur.model.timer.BreakAlarm;
import Altklausur.view.CreateFrame;

public class Main {
    public static void main(String[] args) {
        CustomerSystem customerSystem = new CustomerSystem();
        CreateFrame createFrame = new CreateFrame();
        createFrame.createGUI(customerSystem);
        BreakAlarm breakAlarm = new BreakAlarm();
        breakAlarm.begin();
    }
}
