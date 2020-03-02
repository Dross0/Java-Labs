package Exceptions;

public class CantCreateCommand extends Exception {
    public CantCreateCommand(String message, String cmdName){
        super(message);
        this.cmdName = cmdName;
    }

    public String getCmdName() {
        return cmdName;
    }

    final private String cmdName;
}
