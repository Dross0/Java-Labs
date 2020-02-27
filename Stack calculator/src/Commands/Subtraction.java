package Commands;

import Exceptions.InvalidNumberOfArguments;
import Exceptions.NotEnoughArgumentsForBinaryOperation;
import StackCalculator.StackWithDefinitions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Subtraction implements ICommand {
    private static final Logger logger = Logger.getLogger(Subtraction.class.getName());

    @Override
    public void execute(StackWithDefinitions stackCalc, String... args) throws InvalidNumberOfArguments, NotEnoughArgumentsForBinaryOperation {
        if (args.length != 0){
            throw new InvalidNumberOfArguments("Invalid number of argument of - command", 0, args.length);
        }
        if (stackCalc.size() < 2){
            throw new NotEnoughArgumentsForBinaryOperation("Less than two elements, cant do -");
        }
        double a = stackCalc.pop();
        stackCalc.push(stackCalc.pop() - a);
        if (logger.isLoggable(Level.FINE)){
            logger.fine("Subtraction executed");
        }
    }
}
