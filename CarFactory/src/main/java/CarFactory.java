import Details.Accessory;
import Details.Body;
import Details.Engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarFactory {
    public CarFactory(String pathCfgFile) throws IOException {
        Properties properties = new Properties();
        properties.load(CarFactory.class.getResourceAsStream(pathCfgFile));
        this.engineStorage = new Storage<>(getIntegerProperty(properties,"StorageEngineSize"));
        this.bodyStorage = new Storage<>(getIntegerProperty(properties, "StorageBodySize"));
        this.accessoryStorage = new Storage<>(getIntegerProperty(properties, "StorageAccessorySize"));
        this.carStorage = new Storage<>(getIntegerProperty(properties, "StorageCarSize"));
        this.engineSupplier = new Supplier<>(engineStorage, Engine.class, getIntegerProperty(properties, "EngineProductPeriod"));
        this.bodySupplier = new Supplier<>(bodyStorage, Body.class, getIntegerProperty(properties, "BodyProductPeriod"));
        int accessorySuppliersNumber = getIntegerProperty(properties, "AccessorySuppliers");
        int accessoryProductPeriod = getIntegerProperty(properties, "AccessoryProductPeriod");
        accessorySuppliers = new ArrayList<>();
        for (int i = 0; i < accessorySuppliersNumber; ++i){
            accessorySuppliers.add(new Supplier<>(accessoryStorage, Accessory.class, accessoryProductPeriod));
        }
        int workersNumber = getIntegerProperty(properties, "Workers");
        workers = Executors.newFixedThreadPool(workersNumber);
        int dealersNumber = getIntegerProperty(properties, "Dealers");
        int dealerPeriod = getIntegerProperty(properties, "DealerPeriod");
        dealers = new ArrayList<>();
        for (int i = 0; i < dealersNumber; ++i){
            dealers.add(new Dealer(this, dealerPeriod));
        }
        controller = Executors.newSingleThreadExecutor();
    }

    public void run(){
        for (Dealer dealer: dealers){
            dealer.start();
        }
        for (Supplier<Accessory> supplier: accessorySuppliers){
            supplier.start();
        }
        engineSupplier.start();
        bodySupplier.start();
        controller.execute(new CarStorageController(this));
    }

    public void makeNewCar(){
        workers.submit(new Worker(this));
    }

    private int getIntegerProperty(Properties properties, String key){
        return Integer.parseInt(properties.getProperty(key));
    }

    public Storage<Accessory> getAccessoryStorage() {
        return accessoryStorage;
    }

    public Storage<Engine> getEngineStorage() {
        return engineStorage;
    }

    public Storage<Body> getBodyStorage() {
        return bodyStorage;
    }

    public Car getCar() throws InterruptedException {
        controller.execute(new CarStorageController(this));
        return carStorage.get();
    }

    public void addCar(Car newCar) throws InterruptedException {
        carStorage.add(newCar);
    }

    public int getCarStorageCapacity(){
        return carStorage.getCapacity();
    }

    public int getCarStorageSize(){
        return carStorage.getSize();
    }


    private ExecutorService controller;
    private Storage<Engine> engineStorage;
    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private ExecutorService workers;
    private ArrayList<Dealer> dealers;
    private Supplier<Engine> engineSupplier;
    private Supplier<Body> bodySupplier;
    private ArrayList<Supplier<Accessory>> accessorySuppliers;
    private Storage<Car> carStorage;
}
