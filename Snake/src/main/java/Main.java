
import game.Game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Snake");
//        stage.setWidth(800);
//        stage.setHeight(800);
        Game g = new Game(50, 50);
        for (int i = 1; i < 7; ++i){
            g.addLevel(i);
        }
        GraphicalView view = new GraphicalView(g, stage, 800, 800);
        Controller c = new Controller(g, view);
        c.config(stage);
        stage.show();
    }
}
