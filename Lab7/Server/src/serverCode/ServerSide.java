package serverCode;

import serverCode.managers.CollectionManager;

import java.io.IOException;
import java.net.DatagramSocket;

public class ServerSide {

    //private static final CollectionManager serverCollection;

    public static void main(String[] args) {
        try {
            CollectionManager serverCollection = CollectionManager.getInstance();
            System.out.println("Starting a server moodle.\nWaiting a client...");
            ServerConnection serverConnection = new ServerConnection(new DatagramSocket());
            serverConnection.run();
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            System.err.println("You forgot enter path to file. Use [java -jar Server.jar /path/to/file] for correct using.");
            System.exit(1);
        } catch (IOException ioException) {
            System.err.println("IO exception");
            System.exit(1);
        }
    }
}