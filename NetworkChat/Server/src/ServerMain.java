public class ServerMain {
    public static void main(String [] args) throws InterruptedException {
        Server server = new Server(2048);
        server.addCommand("/users", server::userListCommand);
        server.addCommand("/exit", server::exitUserCommand);
        Thread serverThread = new Thread(server);
        serverThread.start();
        serverThread.join();
    }
}
