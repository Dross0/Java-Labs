package Exceptions;

public class InvalidUserName extends Exception {
    public InvalidUserName(String message, String userName){
        super(message);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    private String userName;
}
