
import game.Game;

import javafx.application.Application;

import javafx.stage.Stage;



public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Snake");
        Game game = new Game(50, 50);
        for (int i = 1; i < 7; ++i){
            game.addLevel(i);
        }
        GraphicalView view = new GraphicalView(game, stage, 800, 800, 300, 400);
        Controller c = new Controller(game, view);
        c.config(stage);
        stage.show();
    }
}
