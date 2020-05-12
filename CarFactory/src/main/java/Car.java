import Details.Accessory;
import Details.Body;
import Details.Engine;


import java.util.HashSet;


public class Car {
    private static int carNumbers = 0;

    public Car(Engine engine, Body body){
        carNumbers++;
        this.engine = engine;
        this.body = body;
        accessoriesList = new HashSet<>();
        this.id = carNumbers;

    }

    public String getEngineId(){
        return engine.getID();
    }

    public String getBodyId(){
        return body.getID();
    }


    public int getId() {
        return id;
    }

    void addAccessory(Accessory accessory){
        accessoriesList.add(accessory);
    }

    void removeAccessory(Accessory accessory){
        accessoriesList.remove(accessory);
    }

    private Engine engine;
    private Body body;
    private HashSet<Accessory> accessoriesList;
    private final int id;


}
