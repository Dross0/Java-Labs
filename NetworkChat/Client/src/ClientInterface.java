public class ClientInterface implements Observer {
    private Client client;

    public ClientInterface(Client client){
        this.client = client;
    }

    @Override
    public void update() {
        Message m = client.getLastMessage();
        System.out.println(m.getMessage());
    }
}
