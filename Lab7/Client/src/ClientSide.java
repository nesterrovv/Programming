public class ClientSide {

    public static void main(String[] args) {
        System.out.println("Starting a client moodle.\nConnecting to server...");
        ClientConnection connection = new ClientConnection();
        connection.work();
    }
}