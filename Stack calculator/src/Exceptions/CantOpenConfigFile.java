package Exceptions;

public class CantOpenConfigFile extends Exception {
    public CantOpenConfigFile(String message, String fileName){
        super(message);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    private final String fileName;
}
