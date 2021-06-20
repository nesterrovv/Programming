public class Client {

    public static void main(String[] args) {
        ClientConnection clientConnection = new ClientConnection();
        boolean access = clientConnection.connect();
        if (access) {
            clientConnection.work();
        } else {
            System.out.println("Permission denied. Please try again.");
        }
    }
}
