public class CarStorageController implements Runnable {
    private CarFactory factory;

    public CarStorageController(CarFactory factory) {
        this.factory = factory;
    }


    @Override
    public void run() {
        int carAmount = factory.getCarsAmount();
        int storageCapacity = factory.getCarStorageCapacity();
        if ((double)carAmount / storageCapacity < 0.3){
            int newCarWorkers = 0;
            while ((double)(carAmount + newCarWorkers) / storageCapacity < 0.8){
                factory.makeNewCar();
                ++newCarWorkers;
            }
        }
    }
}
