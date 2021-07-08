package aufg5;

import java.util.Observable;

public class Model extends Observable {
    private boolean on = false;

    public void toggleSwitch(){
        on = !on;
        setChanged();
        notifyObservers();
    }

    public boolean isOn() {
        return on;
    }

}
