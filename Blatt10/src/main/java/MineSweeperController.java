import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MineSweeperController extends MouseAdapter {
    MineSweeper.Mine field;
    public MineSweeperController(MineSweeper.Mine field){
        this.field = field;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()){
            case MouseEvent.BUTTON1:
                field.check();
                break;
            case MouseEvent.BUTTON3:
                System.out.println("Du bist in der rechten moustaste drin");
                field.flag();
                break;
            default:
                System.out.println("Im default");
        }
    }
}
