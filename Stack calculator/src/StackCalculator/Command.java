package StackCalculator;

public class Command {
    public Command(String name, String[] args){
        this.name = name;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public String[] getArgs() {
        return args;
    }

    private final String name;
    private final String[] args;
}
