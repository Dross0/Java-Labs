package Commands;
import Exceptions.InvalidArgumentType;
import Exceptions.InvalidNumberOfArguments;
import StackCalculator.StackWithDefinitions;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Push implements ICommand {
    private static final Logger logger = Logger.getLogger(Push.class.getName());

    @Override
    public void execute(StackWithDefinitions stackCalc, String... args) throws InvalidNumberOfArguments, InvalidArgumentType {
        if (args.length != 1){
            throw new InvalidNumberOfArguments("Wrong arguments number of push", 1, args.length);
        }
        Double val = stackCalc.getDefine(args[0]);
        if (val == null){
            try {
                val = Double.parseDouble(args[0]);
            }
            catch (NumberFormatException ex){
                throw new InvalidArgumentType("Argument is not double or define", args[0]);
            }
        }
        stackCalc.push(val);
        if (logger.isLoggable(Level.FINE)){
            logger.fine("Push executed");
        }
    }
}
