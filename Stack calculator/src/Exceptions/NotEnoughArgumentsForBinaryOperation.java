package Exceptions;

public class NotEnoughArgumentsForBinaryOperation extends CommandException {
    public NotEnoughArgumentsForBinaryOperation(String message){
        super(message);
    }
}
