public class ClientInterface implements Observer {
    private Client client;

    public ClientInterface(Client client){
        this.client = client;
    }

    @Override
    public void update() {
        Message m = client.getLastMessage();
        if (m.getType() == MessageType.SERVER_RESPONSE){
            System.out.println("SERVER RESPONSE:");
        }
        else if (m.getType() == MessageType.GENERAL_MESSAGE) {
            System.out.print(m.getSenderName() + ": ");
        }
        System.out.println(m.getMessage());
    }
}
