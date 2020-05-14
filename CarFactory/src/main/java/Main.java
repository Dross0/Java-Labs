import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CarFactory factory = new CarFactory("config.cfg");
        factory.run();
        System.in.read();
        factory.stop();
    }
}
