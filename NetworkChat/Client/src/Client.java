

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client implements Runnable, Observable {
    private final String ip;
    private final int port;
    private Socket socket;
    private ObjectOutputStream writer;
    private List<Observer> observers;
    private Message lastMessage;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.observers = new ArrayList<>();

    }

    public void sendMessage(Message message) throws IOException {
        if (message.getMessage().equals("/exit")){
            writer.writeObject(new Message("/exit", MessageType.SERVER_REQUEST));
            close();
            return;
        }
        writer.writeObject(message);
        writer.flush();
    }


    public boolean isConnected(){
        return !socket.isClosed();
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void close() {
        try {
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            socket = new Socket(ip,port);
            writer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            Thread control = new Thread(new ClientController(this));
            control.start();
            while (!socket.isClosed()){
                lastMessage = (Message) reader.readObject();
                notifyObservers();
            }
            writer.close();
            reader.close();
        } catch (Exception ignored) {
        }
        finally {
            close();
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers){
            observer.update();
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
