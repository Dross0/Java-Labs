import Details.Accessory;
import Details.Body;
import Details.Engine;

public class Worker implements Runnable {
    private CarFactory factory;
    private final int period;

    public Worker(CarFactory carFactory, int period){
        factory = carFactory;
        this.period = period;
    }

    @Override
    public void run(){
        try {
            Engine engine = factory.getEngineStorage().get();
            Body body = factory.getBodyStorage().get();
            Accessory accessory = factory.getAccessoryStorage().get();
            Thread.sleep(this.period);
            Car newCar = new Car(engine, body);
            newCar.addAccessory(accessory);
            factory.addCar(newCar);
        } catch (InterruptedException ignored){
        }

    }
}
