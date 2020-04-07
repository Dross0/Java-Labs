import game.Observer;
import javafx.stage.Stage;

public interface View extends Observer {
    void draw();
}
