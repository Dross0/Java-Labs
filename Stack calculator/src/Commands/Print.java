package Commands;

import Exceptions.InvalidNumberOfArguments;
import StackCalculator.StackWithDefinitions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Print implements ICommand {
    private static final Logger logger = Logger.getLogger(Print.class.getName());

    @Override
    public void execute(StackWithDefinitions stackCalc, String... args) throws InvalidNumberOfArguments{
        if (args.length != 0){
            throw new InvalidNumberOfArguments("Invalid number of argument of print command", 0, args.length);
        }
        System.out.println(stackCalc.peek());
        if (logger.isLoggable(Level.FINE)){
            logger.fine("Print executed");
        }
    }
}
