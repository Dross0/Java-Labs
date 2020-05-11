import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientController implements Runnable {
    private Client client;

    public ClientController(Client client){
        this.client = client;
    }

    @Override
    public void run() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (client.isConnected() ){
                String line = consoleReader.readLine();
                Message message = new Message(line, MessageType.GENERAL_MESSAGE);
                client.sendMessage(message);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
