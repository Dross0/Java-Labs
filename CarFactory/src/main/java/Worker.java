import Details.Accessory;
import Details.Body;
import Details.Engine;

public class Worker implements Runnable {
    private CarFactory factory;

    public Worker(CarFactory carFactory){
        factory = carFactory;
    }

    @Override
    public void run(){
        try {
            Engine engine = factory.getEngineStorage().get();
            Body body = factory.getBodyStorage().get();
            Accessory accessory = factory.getAccessoryStorage().get();
            Car newCar = new Car(engine, body);
            newCar.addAccessory(accessory);
            factory.addCar(newCar);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
