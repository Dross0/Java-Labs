public class Dealer extends Thread {
    private final int period;
    private final CarFactory factory;

    public Dealer(CarFactory factory, int period){
        this.period = period;
        this.factory = factory;
    }


    @Override
    public void run(){
        while (!isInterrupted()){
            try {
                Car car = factory.getCar();
                System.out.println("Car=" + car.getId() + "(Engine=" + car.getEngineId() + ", Body=" + car.getBodyId()+ ")");
                Thread.sleep(period);
            } catch (InterruptedException e) {
                break;
            }

        }
    }
}
