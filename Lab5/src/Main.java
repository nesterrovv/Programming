import java.io.*;

public class Main {
    /**
     * Main class for starting a program
     * @author Ivan Nesterov
     * @version 1.0
     * @param args - args for program successfully working
     * @throws IOException - may be throw in the method
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Collection manager v1.0 by Ivan Nesterov is starting!");
        Commander commander = new Commander(new CollectionManager());
        commander.interactiveMod();
    }
}