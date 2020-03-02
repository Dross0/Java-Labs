package StackCalculator;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;


public class Parser {
    public Parser(String path){
        commandsList = new ArrayList<Command>();
        this.path = path;
    }

    public Parser(){
        this(null);
    }

    public void parse() throws FileNotFoundException {
        InputStream file = System.in;
        if (path != null){
            file = new FileInputStream(path);
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.charAt(0) != '#') {
                parse_command(line);
            }
        }
    }

    public ArrayList<Command> getCommandsList() {
        return commandsList;
    }

    private void parse_command(String line){
        String [] arr = line.split(" ");
        commandsList.add(new Command(arr[0], Arrays.copyOfRange(arr, 1, arr.length)));
    }

    private final String path;
    private final ArrayList<Command> commandsList;
}
