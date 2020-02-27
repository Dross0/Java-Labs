package Commands;

import Exceptions.EmptyStackException;
import Exceptions.InvalidNumberOfArguments;
import StackCalculator.StackWithDefinitions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Pop implements ICommand {
    private static final Logger logger = Logger.getLogger(Pop.class.getName());

    @Override
    public void execute(StackWithDefinitions stackCalc, String... args) throws InvalidNumberOfArguments, EmptyStackException {
        if (args.length != 0) {
            throw new InvalidNumberOfArguments("Invalid number of argument of pop command", 0, args.length);
        }
        if (stackCalc.isEmpty()) {
            throw new EmptyStackException("Stack is empty, you cant pop");
        }
        stackCalc.pop();
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Pop executed");
        }
    }
}
