package model.main;

import model.logic.CustomerSystem;
import model.timer.BreakAlarm;
import view.CreateFrame;

public class Main {
    public static void main(String[] args){
        CustomerSystem customerSystem = new CustomerSystem();
        CreateFrame cframe = new CreateFrame();
        cframe.createGUI(customerSystem);
        BreakAlarm alarm = new BreakAlarm();
        alarm.begin();
    }
}
