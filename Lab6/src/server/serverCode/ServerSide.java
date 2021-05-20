package server.serverCode;

/**
 * Class for starting a server
 * @author Ivan Nesterov
 * @version 1.1
 */
public class ServerSide {

    /** Server entry point */
    public static void main(String[] args) {
        try {
            CollectionManager serverCollection = new CollectionManager(args[0]);
            System.out.println("Starting a server moodle.\nWaiting a client...");
            ServerConnection serverConnection = new ServerConnection(serverCollection);
            serverConnection.run();
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            System.err.println("You forgot enter path to file. Use [java -jar Server.jar /path/to/file] for correct using.");
            System.exit(1);
        }
    }
}