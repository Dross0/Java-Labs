import Exceptions.InvalidNumberOfArguments;
import StackCalculator.StackCalculator;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.LogManager;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, InvalidNumberOfArguments, NoSuchMethodException, InvocationTargetException {
        String path = null;
        if (args.length > 1){
            throw new InvalidNumberOfArguments("More than 1 element",  1, args.length);
        }
        else if (args.length == 1){
            path = args[0];
        }
        try{
            FileInputStream cfg = new FileInputStream("config/logger.properties");
            LogManager.getLogManager().readConfiguration(cfg);
            cfg.close();
        }
        catch (IOException ex){
            System.err.println("Could not setup logger configuration: " + ex.toString());
        }
        StackCalculator calc = new StackCalculator(path);
        calc.makeCount();
    }
}
