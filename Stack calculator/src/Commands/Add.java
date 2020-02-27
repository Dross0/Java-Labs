package Commands;


import Exceptions.InvalidNumberOfArguments;
import Exceptions.NotEnoughArgumentsForBinaryOperation;
import StackCalculator.StackWithDefinitions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Add implements ICommand{
    private static final Logger logger = Logger.getLogger(Add.class.getName());

    @Override
    public void execute(StackWithDefinitions stackCalc, String... args) throws InvalidNumberOfArguments, NotEnoughArgumentsForBinaryOperation {
        if (args.length != 0){
            throw new InvalidNumberOfArguments("Invalid number of argument of + command", 0, args.length);
        }
        if (stackCalc.size() < 2){
            throw new NotEnoughArgumentsForBinaryOperation("Less than two elements, cant do +");
        }
        stackCalc.push(stackCalc.pop() + stackCalc.pop());
        if (logger.isLoggable(Level.FINE)){
            logger.fine("Add executed");
        }
    }
}
