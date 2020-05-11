import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Message implements Serializable {
    private String message;
    private MessageType type;
    private Calendar date;
    private int senderID;

    public Message(String message, MessageType type){
        this.message = message;
        this.type = type;
        this.date = new GregorianCalendar();
    }

    public Message(String message, MessageType type, int senderID){
        this(message, type);
        this.senderID = senderID;
    }

    public Message(@NotNull Message msg){
        this(msg.message, msg.type, msg.senderID);
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getSenderID() {
        return senderID;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getType() {
        return type;
    }

    public Calendar getDate() {
        return date;
    }
}
