package game.objects;

import game.Direction;
import game.Point;

import java.util.ArrayList;

public class Snake {
    public Snake(){
        this(0,0);
    }

    public Snake(int x, int y){
        snake = new ArrayList<>();
        currentDir = Direction.DOWN;
        speed = 1;
        snake.add(new Point(x, y));
    }

    public void makeMove(Direction dir){
        if (dir.getReversed() == currentDir){
            dir = dir.getReversed();  //Блокирует движение змейки в противоположном направлении
        }
        currentDir = dir;
        Point head = snake.get(0);
        Point newHead;
        switch (dir){
            case DOWN:
                newHead = new Point(head.getX(), head.getY() + speed);
                break;
            case UP:
                newHead = new Point(head.getX(), head.getY() - speed);
                break;
            case LEFT:
                newHead = new Point(head.getX() - speed, head.getY());
                break;
            case RIGHT:
                newHead =  new Point(head.getX() + speed, head.getY());
                break;
            default:
                newHead = head;
        }
        snake.add(0, newHead);
    }

    public int getSnakeSize(){
        return snake.size();
    }

    public void makeMove(){
        makeMove(currentDir);
    }

    public Point getHead(){
        return snake.get(0);
    }

    public Point getTail(){
        return snake.get(snake.size() - 1);
    }

    public void removeTail(){
        snake.remove(snake.size() - 1);
    }

    public boolean isSnakeBody(Point p){
        /**
         * Return true if p is snake body, return false if p is head or tail
         */
        for (int i = 1; i < snake.size() - 1; ++i){
            if (p.equals(snake.get(i))){
                return true;
            }
        }
        return false;
    }

    private int speed;
    private Direction currentDir;
    private ArrayList<Point> snake;
}
