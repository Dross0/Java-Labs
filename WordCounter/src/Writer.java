import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private FileWriter writer;

    public Writer(String path){
        try{
            writer = new FileWriter(path);
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    public void print(String str) throws IOException {
        writer.write(str);
    }

    public void println(String str) throws IOException {
        print(str);
        writer.write('\n');
    }

    public void close() {
        try {
            writer.close();
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }
}
