import exceptions.InvalidFieldSize;
import game.Direction;
import game.Game;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import java.awt.*;
import java.util.Optional;

public class Controller {
    public Controller(Game game, GraphicalView graphicalView){
        this.game = game;
        this.view = graphicalView;
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
        Button startButton = view.getStartButton();
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    game.reset();
                } catch (InvalidFieldSize invalidFieldSize) {
                    invalidFieldSize.printStackTrace();
                }
                game.setCurrentLevel(view.getChosenLevel());
                game.run();
            }
        });

        Button exitButton = view.getExitButton();
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0); //TODO сделать нормальное завершение программы
            }
        });
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

    private final Game game;
    private final GraphicalView view;
}
