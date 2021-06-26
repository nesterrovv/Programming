package serverCode;

public class Server {

    public static void main(String[] args) {
        System.out.println("Server side of application was started successfully! Connection to database...");
        ServerConnection serverConnection = new ServerConnection();
        serverConnection.work();
    }
}
