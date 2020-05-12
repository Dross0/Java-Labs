import java.io.IOException;
import java.util.logging.LogManager;


public class ServerMain {
    public static void main(String [] args) throws InterruptedException {
        try {
            LogManager.getLogManager().readConfiguration(ServerMain.class.getResourceAsStream("resources/logger.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Server server = new Server(2048);
        server.addCommand("/users", server::userListCommand);
        server.addCommand("/exit", server::exitUserCommand);
        Thread serverThread = new Thread(server);
        serverThread.start();
        serverThread.join();
    }
}
