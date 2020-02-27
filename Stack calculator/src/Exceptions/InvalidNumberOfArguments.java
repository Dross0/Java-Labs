package Exceptions;

public class InvalidNumberOfArguments extends CommandException {
    public InvalidNumberOfArguments(String message, int expectedArgumentsNumber, int receivedArgumentsNumber){
        super(message);
        this.expectedArgumentsNumber = expectedArgumentsNumber;
        this.receivedArgumentsNumber = receivedArgumentsNumber;
    }

    public int getExpectedArgumentsNumber() {
        return expectedArgumentsNumber;
    }

    public int getReceived() {
        return receivedArgumentsNumber;
    }

    private final int expectedArgumentsNumber;
    private final int receivedArgumentsNumber;
}
