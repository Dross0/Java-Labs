package Exceptions;

public class UnknownCommand extends Exception {
    public UnknownCommand(String message, String cmdName){
        super(message);
        this.cmdName = cmdName;
    }

    public String getCmdName() {
        return cmdName;
    }

    private final String cmdName;
}
