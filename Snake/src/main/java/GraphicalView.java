import game.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.util.Optional;


public class GraphicalView implements View {
    public GraphicalView(Game game, Stage window, double gameWindowWidth, double gameWindowHeight, double menuWindowWidth, double menuWindowHeight){
        this.game = game;
        game.addObserver(this);
        this.window = window;
        this.gameWindowHeight = gameWindowHeight;
        this.gameWindowWidth = gameWindowWidth;
        this.menuWindowHeight = menuWindowHeight;
        this.menuWindowWidth = menuWindowWidth;
        this.menuScene = new Scene(new Group(), menuWindowWidth, menuWindowHeight);
        this.gameScene = new Scene(new Group(), gameWindowWidth, gameWindowHeight);
        initMenuScene();
        switchOnMenuScene();
    }


    @Override
    public void draw() {
        GameStatus status = game.getStatus();
        if (status == GameStatus.STARTING){
            switchOnNewGameScene();
            drawGameContext();
        }
        else if (status == GameStatus.PLAY){
            drawGameContext();
        }
        else {
            final String outMsg;
            if (status == GameStatus.WIN){
                outMsg = "Поздравляю, вы выйграли. Ваш счет: " + game.getScore();
            }
            else if (status == GameStatus.LOSE){
                outMsg = "Вы проиграли. Ваш счет: " + game.getScore();
            }
            else{
                outMsg = "";
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    menuLabel.setText(outMsg);
                }
            });
            switchOnMenuScene();
        }

    }

    @Override
    public void update() {
        draw();
    }

    public void switchOnNewGameScene(){
        initGameScene();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                window.setScene(gameScene);
            }
        });
    }

    public void switchOnMenuScene(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                window.setScene(menuScene);
            }
        });

    }

    private void drawGameContext(){
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

    private void initMenuScene(){
        menuLabel = new Label("");
        menuLabel.setAlignment(Pos.CENTER);
        menuLabel.setMaxHeight(30);
        menuLabel.setMaxWidth(menuScene.getWidth() - 30);
        startButton = new Button("Start");
        startButton.setStyle("-fx-font: 22 arial; -fx-base: #008000;");
        startButton.setMaxWidth(menuWindowWidth - 30);
        startButton.setMaxHeight(30);
        exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font: 22 arial; -fx-base: #FF0000;");
        exitButton.setMaxWidth(menuWindowHeight - 30);
        exitButton.setMaxHeight(30);
        ObservableList<Integer> levels = FXCollections.observableArrayList(game.getLevels());
        levelChoiceBox = new ChoiceBox<>(levels);
        levelChoiceBox.setValue(levels.get(0));
        FlowPane menuPane = new FlowPane(Orientation.VERTICAL, 0, 50, menuLabel, startButton, levelChoiceBox, exitButton);
        menuPane.setAlignment(Pos.CENTER);
        menuScene.setRoot(menuPane);
    }


    private void initGameScene(){
        int fieldWidth = game.getFieldWidth();
        int fieldHeight = game.getFieldHeight();
        rectangles = new Rectangle[fieldHeight][fieldWidth];
        double rectWidth = gameWindowWidth / fieldWidth;
        double rectHeight =  (gameWindowHeight - 50) / fieldHeight;
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
        scoreLabel = new Label("Score: ");
        FlowPane pane = new FlowPane(gp, scoreLabel);
        gameScene.setRoot(pane);
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public int getChosenLevel() {
        return levelChoiceBox.getValue();
    }

    public double getGameWindowWidth() {
        return gameWindowWidth;
    }

    public void setGameWindowWidth(double gameWindowWidth) {
        this.gameWindowWidth = gameWindowWidth;
    }

    public double getGameWindowHeight() {
        return gameWindowHeight;
    }

    public void setGameWindowHeight(double gameWindowHeight) {
        this.gameWindowHeight = gameWindowHeight;
    }

    public double getMenuWindowWidth() {
        return menuWindowWidth;
    }

    public void setMenuWindowWidth(double menuWindowWidth) {
        this.menuWindowWidth = menuWindowWidth;
    }

    public double getMenuWindowHeight() {
        return menuWindowHeight;
    }

    public void setMenuWindowHeight(double menuWindowHeight) {
        this.menuWindowHeight = menuWindowHeight;
    }

    ChoiceBox<Integer> levelChoiceBox;
    Label menuLabel;
    double gameWindowWidth;
    double gameWindowHeight;
    double menuWindowWidth;
    double menuWindowHeight;
    Scene menuScene;
    Scene gameScene;
    Button startButton;
    Button exitButton;
    Label scoreLabel;
    Rectangle[][] rectangles;
    final Game game;
    final Stage window;
}
