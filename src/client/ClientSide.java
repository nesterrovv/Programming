package client;

public class ClientSide {

    public static void main(String[] args) {
        System.out.println("Starting a client moodle.\nConnecting to server...");
        System.out.println("You can write a command.");
        ClientConnection connection = new ClientConnection();
        connection.work();
    }
}