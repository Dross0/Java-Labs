package Details;


public class Engine implements Detail{
    private static int engineNumbers = 0;

    public Engine(){
        engineNumbers++;
        id = engineNumbers;
    }

    @Override
    public String getID() {
        return "Engine №" + id;
    }


    private final int id;
}
