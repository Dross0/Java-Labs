

import Exceptions.InvalidUserName;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class Server implements Runnable{
    private final Logger logger = Logger.getLogger(Server.class.getName());

    private int port;
    private ServerSocket serverSocket;
    private ConcurrentHashMap<User, RequestHandler> clients= new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Consumer<Message>> commands = new ConcurrentHashMap<>();

    public Server(int port) {
        this.port = port;
    }

    public void addCommand(String cmdName, Consumer<Message> handler){
        commands.put(cmdName, handler);
    }

    public void removeCommand(String cmdName){
        commands.remove(cmdName);
    }

    public Optional<Consumer<Message>> getCommand(String cmdName){
        if (commands.containsKey(cmdName)){
            return Optional.of(commands.get(cmdName));
        }
        return Optional.empty();
    }


    public void addUser(User user, RequestHandler handler) throws InvalidUserName {
        if (!userValidation(user)){
            throw new InvalidUserName("This name is busy", user.getName());
        }
        clients.put(user, handler);
    }

    public Set<User> getUserList(){
        return clients.keySet();
    }

    public void removeUser(User user){
        clients.remove(user);
    }

    public void userListCommand(Message message){
        Set<User> userSet = getUserList();
        StringBuilder users = new StringBuilder();
        for (User user: userSet){
            String tmp = user.toString() + "\n";
            users.append(tmp);
        }
        Message response = new Message(users.toString(), MessageType.SERVER_RESPONSE, message.getSenderID());
        sendMessage(response);
    }

    public void exitUserCommand(Message message){
        int senderId = message.getSenderID();
        for (User user: clients.keySet()){
            if (user.getId() == senderId){
                logger.info("User=" + user.getName() + " disconnected");
                RequestHandler handler = clients.get(user);
                handler.close();
                removeUser(user);
                return;
            }
        }
    }

    private boolean userValidation(User user) {
        for (User u: clients.keySet()){
            if (u.getName().equals(user.getName())){
                return false;
            }
        }
        return true;
    }


    void close(){
        if (serverSocket != null){
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            logger.info("Server starting on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Client connected " + clientSocket.getInetAddress() + " " + clientSocket.getPort());
                Thread t = new Thread(new RequestHandler(clientSocket, this));
                t.start();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void sendMessage(Message message){
        switch (message.getType()){
            case GENERAL_MESSAGE:
                sendToAllMessage(message);
                break;
            case SERVER_RESPONSE:
                sendServerResponseMessage(message);
                break;
        }

    }

    private void sendServerResponseMessage(Message message) {
        for (User user: clients.keySet()){
            if (user.getId() == message.getSenderID()){
                clients.get(user).sendMessage(message);
                break;
            }
        }
    }

    private void sendToAllMessage(Message message) {
        for (RequestHandler handler: clients.values()){
            handler.sendMessage(message);
        }
    }
}
