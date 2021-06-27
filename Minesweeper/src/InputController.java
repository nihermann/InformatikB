import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputController extends MouseAdapter {
    private GameModel model;
    private int x, y;

    public InputController(GameModel model, int x, int y) {
        this.model = model;
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!model.isRunning()) return;

        if(e.getButton() == 1){
            model.updateField(this.x, this.y);
        }
        if(e.getButton() == 3){
            model.updateMarked(this.x, this.y);
        }
    }
}
