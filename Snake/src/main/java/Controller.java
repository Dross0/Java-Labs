import game.Direction;
import game.Game;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import java.util.Optional;

public class Controller {
    public Controller(Game game){
        this.game = game;
    }


    public void config(Stage stage){
        EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                Optional<Direction> dir = getDirFromKeyCode(keyEvent.getCode());
                game.updateField(dir);
            }
        };
        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
    }

    public void start(){
        game.run();
    }


    private Optional<Direction> getDirFromKeyCode(final KeyCode keyCode){
        if (keyCode.equals(KeyCode.W) || keyCode.equals(KeyCode.UP)){
            return Optional.of(Direction.UP);
        }
        else if (keyCode.equals(KeyCode.A) || keyCode.equals(KeyCode.LEFT)){
            return Optional.of(Direction.LEFT);
        }
        else if (keyCode.equals(KeyCode.D) || keyCode.equals(KeyCode.RIGHT)){
            return Optional.of(Direction.RIGHT);
        }
        else if (keyCode.equals(KeyCode.S) || keyCode.equals(KeyCode.DOWN)){
            return Optional.of(Direction.DOWN);
        }
        return Optional.empty();
    }

    private Game game;
}
