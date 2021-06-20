package serverCode;

public class Server {

    public static void main(String[] args) {
        System.out.println("Server application was started! Connection to database...");
        ServerConnection serverConnection = new ServerConnection();
        serverConnection.authorize();
        serverConnection.work(serverConnection.getEnteredUsername());
    }

}
