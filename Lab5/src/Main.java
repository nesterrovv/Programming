import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Collection manager v1.0 by Ivan Nesterov is starting!");
        Commander commander = new Commander(new CollectionManager());
        commander.interactiveMod();
    }
}