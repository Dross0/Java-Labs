package Details;


public class Accessory implements Detail {
    private static int accessoryNumbers = 0;

    public Accessory(){
        accessoryNumbers++;
        id = accessoryNumbers;
    }

    @Override
    public String getID() {
        return "Body №" + id;
    }


    private final int id;
}
