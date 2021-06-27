import javax.swing.*;

public class Timer extends Thread {
    private final JLabel label;
    private final GameModel model;
    private long startTime;
    private double endTime = -1;

    public synchronized Double getEndTime() {
        notifyAll();
        while (endTime == -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return endTime;
    }

    public Timer(JLabel l, GameModel m){
        this.label = l;
        this.model = m;
    }

    @Override
    public void start(){
        startTime = System.currentTimeMillis();
        super.start();
    }

    @Override
    public void run(){
        while(model.isRunning()){
            this.label.setText("Time: " + ((double)(System.currentTimeMillis() - this.startTime) / 1000));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endTime = ((double)(System.currentTimeMillis() - this.startTime) / 1000);
        this.label.setText("Final Time: " + endTime);
        getEndTime(); // for notifyAll();
    }
}
