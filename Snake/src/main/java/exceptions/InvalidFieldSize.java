package exceptions;

public class InvalidFieldSize extends Exception {
    public InvalidFieldSize(String msg, int width, int height){
        super(msg);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private final int width;
    private final int height;
}
