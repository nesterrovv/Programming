package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection {

    private static Scanner fromKeyboard;
    private static ObjectOutputStream toServer;
    private static ObjectInputStream fromServer;

    public ClientConnection() {}

    public void work() {
        try (Scanner scanner = new Scanner(System.in)) {
            fromKeyboard = scanner;
            while (true) {
                try (Socket outcoming = new Socket(InetAddress.getLocalHost(), 8800)) {
                    outcoming.setSoTimeout(5000);
                    try (ObjectOutputStream outputStream = new ObjectOutputStream(outcoming.getOutputStream());
                         ObjectInputStream inputStream = new ObjectInputStream(outcoming.getInputStream())) {
                        toServer = outputStream;
                        fromServer = inputStream;
                        interactiveMode();
                        System.out.println("Finishing a program...");
                    }
                } catch (IOException e) {
                    System.err.println("Server is not available at the moment. Reconnect? (enter {yes} or {no})?");
                    String answer;
                    while (!(answer = fromKeyboard.nextLine()).equals("yes")) {
                        switch (answer) {
                            case "":
                                break;
                            case "no":
                                exit();
                                break;
                            default: System.out.println("Please write a correct answer.");
                        }
                    }
                    System.out.print("Connecting...");
                }
            }
        }
    }

    private void interactiveMode() throws IOException {
        try {
            System.out.println((String) fromServer.readObject());
            String command;
            while (!(command = fromKeyboard.nextLine()).equals("exit")) {
                String[] parsedCommand = command.trim().split(" ", 2);
                switch (parsedCommand[0]) {
                    case "":
                        break;
                    case "import":
                        try {
                            toServer.writeObject(importingFile(parsedCommand[1]));
                            System.out.println((String) fromServer.readObject());
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found.");
                        } catch (SecurityException e) {
                            System.out.println("File cannot be read.");
                        } catch (IOException e) {
                            System.out.println("Something bad with file. Try again");
                        }
                        break;
                    default:
                        toServer.writeObject(command);
                        System.out.println((String) fromServer.readObject());
                }
            }
            exit();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }
    }

    private String importingFile(String path) throws SecurityException, IOException {
        File localCollection = new File(path);
        String extension = localCollection.getAbsolutePath().substring(localCollection.getAbsolutePath().lastIndexOf(".") + 1);
        if (!localCollection.exists() | localCollection.length() == 0  | !extension.equals("xml"))
            throw new FileNotFoundException();
        if (!localCollection.canRead()) throw new SecurityException();
        try (BufferedReader inputStreamReader = new BufferedReader(new FileReader(localCollection))) {
            String nextLine;
            StringBuilder result = new StringBuilder();
            while ((nextLine = inputStreamReader.readLine()) != null) result.append(nextLine);
            return "import " + result.toString();
        }
    }

    private void exit() {
        System.out.println("Finishing a program.");
        System.exit(0);
    }
}