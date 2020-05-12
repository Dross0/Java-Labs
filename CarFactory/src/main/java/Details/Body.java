package Details;


public class Body implements Detail{
    private static int bodyNumbers = 0;

    public Body(){
        bodyNumbers++;
        id = bodyNumbers;
    }

    @Override
    public String getID() {
        return "Body №" + id;
    }


    private final int id;
}
