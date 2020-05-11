import Exceptions.InvalidUserName;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;
import java.util.function.Consumer;

public class RequestHandler implements Runnable {
    private Socket socket;
    private Server server;
    private ObjectOutputStream writer;


    public RequestHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void close() {
        if (socket != null) {
            try {
                socket.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void messageProcessing(Message message){
        Optional<Consumer<Message>> handler = server.getCommand(message.getMessage());
        if (handler.isEmpty()){
            server.sendMessage(message);
        }
        else{
            handler.get().accept(message);
        }
    }

    @Override
    public void run() {
        try {
            writer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            Message loginMessage = (Message) reader.readObject();
            User user = new User(loginMessage.getMessage(), socket.getPort());
            server.addUser(user, this);
            sendMessage(new Message("User: " + user.getName() + " successful registration", MessageType.SERVER_RESPONSE));
            while (true){
                Message message = (Message) reader.readObject();
                message.setSenderID(user.getId());
                System.out.println("MESSAGE: " + message.getMessage() + " TYPE: " + message.getType() + " FROM: " + message.getSenderID());
                messageProcessing(message);
            }
        }
        catch (InvalidUserName ex){
            sendMessage(new Message("This name is busy", MessageType.SERVER_RESPONSE));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void sendMessage(Message message) {
        try {
            writer.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}