package game;

import exceptions.InvalidFieldSize;
import game.objects.Snake;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements Observable{
    public Game(int width, int height) throws InvalidFieldSize {
        observers = new ArrayList<>();
        levels = new ArrayList<>();
        field = new GameField(width, height);
        reset();
    }

    public Game(int size) throws InvalidFieldSize {
        this(size, size);
    }


    public void reset() throws InvalidFieldSize {
        snake = new Snake();
        if (field != null) {
            field = new GameField(field.getWidth(), field.getHeight());
        }
        score = 0;
        currentLevel = 1;
        if (!levels.contains(currentLevel)) {
            addLevel(currentLevel);
        }
        field.set(snake.getHead().getY(), snake.getHead().getX(), CellType.SNAKE_BODY);
        fruitCoordinates = makeNewFruit();
        tailForRemove = Optional.empty();
        status = GameStatus.STOP;
    }


    public void updateField(Optional<Direction> dir){
        Point tailForRemove = snake.getTail();
        if (dir.isPresent()){
            snake.makeMove(dir.get());
        }
        else{
            snake.makeMove();
        }
        Point newHead = snake.getHead();
        boolean isLose = checkLose(newHead);
        if (isLose || snake.isSnakeBody(newHead)){
            status = GameStatus.LOSE;
            notifyObservers();
            return;
        }
        if (newHead.equals(fruitCoordinates.get())){
            score++;
            fruitCoordinates = makeNewFruit();
            if (checkWin()){
                status = GameStatus.WIN;
                notifyObservers();
                return;
            }
            this.tailForRemove = Optional.empty();
        }
        else {
            this.tailForRemove = Optional.of(tailForRemove);
            snake.removeTail();
            field.set(tailForRemove.getY(), tailForRemove.getX(), CellType.EMPTY);
        }
        field.set(newHead.getY(), newHead.getX(), CellType.SNAKE_BODY);
        notifyObservers();
    }

    public void run(){
        status = GameStatus.STARTING;
        notifyObservers();
        status = GameStatus.PLAY;
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                updateField(Optional.empty());
                if (status != GameStatus.PLAY){
                    timer.cancel();
                }
            }
        };
        timer.schedule(tt, 0, 1000 / (5 * currentLevel));
    }


    private boolean checkLose(Point p){
        return p.getX() < 0 || p.getX() >= field.getWidth() || p.getY() < 0 || p.getY() >= field.getHeight();
    }

    private boolean checkWin(){
        return fruitCoordinates.isEmpty(); //Если не нашлось пустой клетки для фрукта, то значит победа, так как змейка занимает все поле
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers){
            observer.update();
        }
    }

    private Optional<Point> makeNewFruit(){
        Optional<Point> newFruit = Optional.ofNullable(field.getRandomEmptyCell());
        if (newFruit.isEmpty()){
            return newFruit;
        }
        field.set(newFruit.get().getY(), newFruit.get().getX(), CellType.FRUIT);
        return newFruit;
    }

    public GameField getField() {
        return field;
    }

    public GameStatus getStatus() {
        return status;
    }

    public int getFieldWidth(){
        return field.getWidth();
    }

    public int getFieldHeight(){
        return field.getHeight();
    }

    public Point getFruitCoordinates(){
        return fruitCoordinates.get();
    }

    public Point getSnakeHead(){
        return snake.getHead();
    }

    public Optional<Point> getTailForRemove() {
        return tailForRemove;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Integer> getLevels() {
        return levels;
    }

    public void addLevel(int levelNumber){
        if (!levels.contains(levelNumber)) {
            levels.add(levelNumber);
        }
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    private int score;
    private int currentLevel;
    private ArrayList<Integer> levels;
    private Optional<Point> tailForRemove;
    private GameStatus status;
    private Optional<Point> fruitCoordinates;
    private Snake snake;
    private GameField field;
    private ArrayList<Observer> observers;


}
