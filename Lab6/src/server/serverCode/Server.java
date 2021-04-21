import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        // Loop for connecting to server
        for (; ; ) {
            try {
                server = new ServerSocket(4242);
                System.out.println("Server was started!");
                clientSocket = server.accept();
                // do something here
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    String command = in.readLine();
                    System.out.println(command);
                    out.write("Hello, it is Server! Your message: " + command + "\n");
                    out.flush();
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
                break;
            } catch (IOException ioException) {
                System.out.println("Server is not available. Reconnection...");
            }
        }
    }
}