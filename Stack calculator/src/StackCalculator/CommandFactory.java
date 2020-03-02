package StackCalculator;
import Commands.*;
import Exceptions.CantOpenConfigFile;
import Exceptions.UnknownCommand;
import Exceptions.CantCreateCommand;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommandFactory  {
    private static final Logger logger = Logger.getLogger(CommandFactory.class.getName());

    private CommandFactory() throws CantOpenConfigFile, CantCreateCommand {
        commands_table = new HashMap<>();
        Properties props = new Properties();
        final String configFileName = "config/commands.ini";
        try {
            props.load(new FileInputStream(new File(configFileName)));
        }
        catch (IOException ex){
            logger.log(Level.SEVERE, "Exception: ", ex);
            throw  new CantOpenConfigFile("Cant open commands config file", configFileName);
        }
        for (Entry entry: props.entrySet()){
            String res = (String)entry.getValue();
            try {
                commands_table.put((String) entry.getKey(), Class.forName(res));
            }
            catch (ClassNotFoundException ex){
                logger.log(Level.SEVERE, "Exception: ", ex);
                throw new CantCreateCommand("Cant create class with forName from class name", res);
            }
        }
    }

    static public CommandFactory instance() throws CantOpenConfigFile, CantCreateCommand {
        CommandFactory localFactory = factory;
        if (localFactory == null){
            synchronized (CommandFactory.class){
                localFactory = factory;
                if (localFactory == null){
                    factory = localFactory = new CommandFactory();
                }
            }
        }
        return localFactory;
    }

    public ICommand getCommand(String cmdName) throws UnknownCommand, CantCreateCommand {
        if (!commands_table.containsKey(cmdName)){
            throw new UnknownCommand("Unknown command name", cmdName);
        }
        ICommand cmd;
        try{
            cmd = (ICommand) commands_table.get(cmdName).getDeclaredConstructor().newInstance();
        }
        catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex){
            logger.log(Level.SEVERE, "Exception: ", ex);
            throw new CantCreateCommand("Cant create command from class", cmdName);
        }
        return cmd;
    }


    static volatile private CommandFactory factory = null;
    private final HashMap<String, Class> commands_table;
}
