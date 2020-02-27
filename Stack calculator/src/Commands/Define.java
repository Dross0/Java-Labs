package Commands;

import Exceptions.InvalidArgumentType;
import Exceptions.InvalidNumberOfArguments;
import StackCalculator.StackWithDefinitions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Define implements ICommand {
    private static final Logger logger = Logger.getLogger(Define.class.getName());

    @Override
    public void execute(StackWithDefinitions stackCalc, String... args) throws InvalidNumberOfArguments, InvalidArgumentType {
        if (args.length != 2){
            throw new InvalidNumberOfArguments("Invalid number of argument of define command", 2, args.length);
        }
        double val;
        try{
            val = Double.parseDouble(args[1]);
        }
        catch (NumberFormatException ex){
            throw new InvalidArgumentType("Second argument must be a number", args[1]);
        }
        stackCalc.define(args[0], val);
        if (logger.isLoggable(Level.FINE)){
            logger.fine("Define executed");
        }
    }
}
