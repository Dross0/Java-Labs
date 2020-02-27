package Exceptions;

public class MultipleDefinition extends Exception {
    public MultipleDefinition(String message, String key, Double prevValue, Double newValue){
        super(message);
        this.key = key;
        this.prevValue = prevValue;
        this.newValue = newValue;
    }

    public String getKey() {
        return key;
    }

    public double getPrevValue() {
        return prevValue;
    }

    public double getNewValue() {
        return newValue;
    }

    private final String key;
    private final double prevValue;
    private final double newValue;
}
