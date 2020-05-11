

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client implements Runnable, Observable {
    private final String ip;
    private final int port;
    private ObjectOutputStream writer;
    private boolean isConnected;
    private List<Observer> observers;
    private Message lastMessage;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.isConnected = false;
        this.observers = new ArrayList<>();
    }

    public void sendMessage(Message message) throws IOException {
        if (message.getMessage().equals("exit")){
            isConnected = false;
            return;
        }
        writer.writeObject(message);
        writer.flush();
    }


    public boolean isConnected(){
        return isConnected;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip,port);
            isConnected = true;
            writer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            while (isConnected){
                lastMessage = (Message) reader.readObject();
                notifyObservers();
            }
            writer.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
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
