package StackCalculator;
import Commands.*;
import Exceptions.UnknownCommand;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.io.FileInputStream;


public class CommandFactory  {
    private CommandFactory() throws IOException, ClassNotFoundException {
        commands_table = new HashMap<>();
        Properties props = new Properties();
        props.load(new FileInputStream(new File("config/commands.ini")));
        for (Entry entry: props.entrySet()){
            String res = (String)entry.getValue();
            commands_table.put((String) entry.getKey(), Class.forName(res));
        }
    }

    static public CommandFactory instance() throws IOException, ClassNotFoundException {
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

    public ICommand getCommand(String cmdName) throws UnknownCommand, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (!commands_table.containsKey(cmdName)){
            throw new UnknownCommand("Unknown command name", cmdName);
        }
        return (ICommand) commands_table.get(cmdName).getDeclaredConstructor().newInstance();
    }


    static volatile private CommandFactory factory = null;
    private final HashMap<String, Class> commands_table;
}
