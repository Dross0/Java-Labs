package Exceptions;

public class UnknownDefinition extends Exception {
    public UnknownDefinition(String message, String key){
        super(message);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    private final String key;
}
