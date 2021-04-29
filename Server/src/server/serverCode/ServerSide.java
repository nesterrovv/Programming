package server.serverCode;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {

    private static final CollectionManager serverCollection = new CollectionManager();

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8800)) {
            System.out.println("Server is accepting a client. " + "\nPort " + server.getLocalPort() +
                    " / Address " + InetAddress.getLocalHost() + ".\nWaiting a connection of clients... ");
            Thread pointer = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Waiting...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.print("\n");
                        Thread.currentThread().interrupt();
                    }
                }
            });
            pointer.setDaemon(true);
            pointer.start();
            while (true) {
                Socket incoming = server.accept();
                pointer.interrupt();
                System.out.println(incoming + " is connected to server.");
                Runnable r = new ServerConnection(serverCollection, incoming);
                Thread t = new Thread(r);
                t.start();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}