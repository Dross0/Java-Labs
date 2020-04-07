import game.*;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Optional;

public class GraphicalView implements View {
    public GraphicalView(Game game, Scene scene, double windowHeight, double windowWidth){
        this.game = game;
        game.addObserver(this);
        this.scene = scene;
        int fieldWidth = game.getFieldWidth();
        int fieldHeight = game.getFieldHeight();
        rectangles = new Rectangle[fieldHeight][fieldWidth];
        double rectWidth = windowWidth / fieldWidth;
        double rectHeight =  (windowHeight - 50) / fieldHeight;
        GridPane gp = new GridPane();
        for (int i = 0; i < fieldHeight; ++i) {
            for (int j = 0; j < fieldWidth; ++j) {
                  rectangles[i][j] = new Rectangle();
                  rectangles[i][j].setWidth(rectWidth);
                  rectangles[i][j].setHeight(rectHeight);
                  rectangles[i][j].setFill(Color.WHITE);
                  gp.add(rectangles[i][j], j, i);
            }
        }
        scoreLabel = new Label("Score: 1");
        FlowPane pane = new FlowPane(gp, scoreLabel);

        scene.setRoot(pane);
    }


    @Override
    public void draw() {
        Optional<Point> tailForRemove = game.getTailForRemove();
        Point newHead = game.getSnakeHead();
        Point fruitCoordinates = game.getFruitCoordinates();
        if (tailForRemove.isPresent()){
            rectangles[tailForRemove.get().getY()][tailForRemove.get().getX()].setFill(Color.WHITE);
        }
        else {
            int new_score = game.getScore();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    scoreLabel.setText("Score: " + new_score);
                }
            });

        }
        rectangles[newHead.getY()][newHead.getX()].setFill(Color.GREEN);
        rectangles[fruitCoordinates.getY()][fruitCoordinates.getX()].setFill(Color.ORANGE);

    }

    @Override
    public void update() {
        draw();
    }

    Label scoreLabel;
    Rectangle[][] rectangles;
    final Game game;
    Scene scene;
}
