package game;

public enum Direction {
    DOWN,
    UP,
    RIGHT,
    LEFT;

    public Direction getReversed(){
        switch (this){
            case DOWN:
                return UP;
            case UP:
                return DOWN;
            case RIGHT:
                return LEFT;
            case LEFT:
                return RIGHT;
        }
        return this;
    }
}
