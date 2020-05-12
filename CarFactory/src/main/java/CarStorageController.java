public class CarStorageController implements Runnable {
    private CarFactory factory;

    public CarStorageController(CarFactory factory) {
        this.factory = factory;
    }


    @Override
    public void run() {
        if ((double)factory.getCarStorageSize() / factory.getCarStorageCapacity() < 0.8){
            factory.makeNewCar();
        }
    }
}
