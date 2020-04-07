package game;

import exceptions.InvalidFieldSize;


public class GameField {
    public GameField(int width, int height) throws InvalidFieldSize {
        if (width <= 0 || height <= 0){
            throw new InvalidFieldSize("One of sizes is negative or zero", width, height);
        }
        field = new Cell[height][width];
        for (int i = 0; i < height; ++i){
            for (int j = 0; j < width; ++j){
                field[i][j] = new Cell(j, i);
            }
        }
        this.width = width;
        this.height = height;
    }

    public GameField(int size) throws InvalidFieldSize {
        this(size, size);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell get(int i, int j){
        return field[i][j];
    }

    public void set(int i, int j, CellType type){
        field[i][j].setType(type);
    }

    public Cell getRandomEmptyCell(){
        Cell randomEmptyCell;
        do{
            int randomX = (int) (Math.random() * (width - 1));
            int randomY = (int) (Math.random() * (height - 1));
            randomEmptyCell = field[randomY][randomX];

        } while(randomEmptyCell.getType() != CellType.EMPTY);
        return randomEmptyCell;
    }

    private Cell [][] field;
    private int width;
    private int height;
}
