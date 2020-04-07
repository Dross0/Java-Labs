
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
        stage.setWidth(800);
        stage.setHeight(800);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        Game g = new Game(50, 50);
        GraphicalView view = new GraphicalView(g, scene, stage.getHeight(), stage.getWidth());
        Controller c = new Controller(g);
        c.config(stage);
        c.start();
        stage.setScene(scene);
        stage.show();
    }
}
