import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Commander commander = new Commander(new CollectionManager());
        commander.interactiveMod();
    }
}