public class Client {

    public static void main(String[] args) {
        System.out.println("Application was started!");
        ClientConnection clientConnection = new ClientConnection();
        //clientConnection.connect();
        clientConnection.work();

    }
}
