

public class ClientMain {
    public static void main(String[] args) throws InterruptedException{
        Client client = new Client("127.0.0.1", 2048);
        ClientInterface ci = new ClientInterface(client);
        client.addObserver(ci);
        ClientController clientController = new ClientController(client);
        Thread control = new Thread(clientController);
        Thread s = new Thread(client);
        s.start();
        control.start();
        s.join();
    }
}
