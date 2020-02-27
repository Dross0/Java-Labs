package Exceptions;

public class InvalidArgumentType extends CommandException {
    public InvalidArgumentType(String message, String arg){
        super(message);
        this.argument = arg;
    }

    public String getArgument() {
        return argument;
    }

    private final String argument;
}
