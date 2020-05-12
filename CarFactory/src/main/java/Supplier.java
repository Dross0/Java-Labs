import Details.Detail;



public class Supplier<T extends Detail> extends Thread{
    public Supplier(Storage<T> storage, Class<T> detailMaker, long productionPeriod){
        this.storage = storage;
        this.detailMaker = detailMaker;
        this.productionPeriod = productionPeriod;
    }

    @Override
    public void run(){
        while (!isInterrupted()){
            try {
                storage.add(detailMaker.getDeclaredConstructor().newInstance());
                Thread.sleep(productionPeriod);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private long productionPeriod;
    private Storage<T> storage;
    private Class<T> detailMaker;
}
