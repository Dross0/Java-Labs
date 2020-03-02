import Exceptions.CantCreateCommand;
import Exceptions.CantOpenConfigFile;
import Exceptions.InvalidNumberOfArguments;
import StackCalculator.StackCalculator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {
    static private final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException, InvalidNumberOfArguments, CantOpenConfigFile, CantCreateCommand {
        String path = null;
        if (args.length > 1){
            throw new InvalidNumberOfArguments("More than 1 element",  1, args.length);
        }
        else if (args.length == 1){
            path = args[0];
        }
        final String loggerConfigFileName = "config/logger.properties";
        try{
            FileInputStream cfg = new FileInputStream(loggerConfigFileName);
            LogManager.getLogManager().readConfiguration(cfg);
            cfg.close();
        }
        catch (IOException ex){
            logger.log(Level.SEVERE, "Exception: ", ex);
            throw new CantOpenConfigFile("Cant open logger config file", loggerConfigFileName);
        }
        StackCalculator calc = new StackCalculator(path);
        calc.makeCount();
    }
}
