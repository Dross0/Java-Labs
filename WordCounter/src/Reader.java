import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader {
    private FileReader fr;
    private Scanner scanner;

    public Reader(String path){
        try{
            fr = new FileReader(path);
            scanner = new Scanner(fr);
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public boolean hasNextLine(){
        return scanner.hasNextLine();
    }

    public String getNextLine(){
        return scanner.nextLine();
    }

    public void close(){
        try {
            fr.close();
            scanner.close();
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }
}
