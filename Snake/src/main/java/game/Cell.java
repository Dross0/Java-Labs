package game;

public class Cell extends Point {
    public Cell(int x, int y, CellType type){
        super(x, y);
        this.type = type;
    }

    public Cell(int x, int y){
        this(x, y, CellType.EMPTY);
    }

    public Cell(Point p, CellType type){
        this(p.getX(), p.getY(), type);
    }

    public Cell(Point p){
        this(p.getX(), p.getY(), CellType.EMPTY);
    }

    public CellType getType() {
        return type;
    }

    void setType(CellType type){
        this.type = type;
    }

    @Override
    public boolean equals(Object p){
        if (this == p){
            return true;
        }
        if (!(p instanceof Cell)){
            return false;
        }
        Cell tmp = (Cell) p;
        return tmp.getX() == this.getX() && tmp.getY() == this.getY() && tmp.getType() == this.getType();
    }

    @Override
    public int hashCode(){
        int hash = super.hashCode();
        return hash + type.ordinal();
    }

    private CellType type;
}
