package serverCode;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import com.sun.org.apache.xalan.internal.xsltc.dom.AdaptiveResultTreeImpl;
import commands.*;

public class ServerConnection {

    String URL; // URL of Postgres database on port 4242
    String username; // username for connection to Helios
    String password; // password for connection to Helios
    DatagramSocket datagramSocket;
    //Connection connection;
    boolean permission_to_work; // boolean flag which allow to start method run for using commands after verefication
    byte[] buf = new byte[60000]; // array for receiving data for authorizing from client
    byte[] bufIn = new byte[60000]; // array for receiving package with command from user
    byte bufOut[] = new byte[60000];; // array for sending an answer (result of command execution) to client
    DatabaseManager databaseManager;
    private int id;
    String enteredUsername;

    public ServerConnection() {
        try {
            this.URL = "jdbc:postgresql://localhost:4242/studs";
            this.username = "s312621";
            this.password = "ctj483";
            databaseManager = new DatabaseManager(URL, username, password);
            datagramSocket = new DatagramSocket(4241);
        } catch (SocketException socketException) {
            System.out.println("Socket is not available.");
        }
    }

    /**
     * Method for authorizing user
     * @return boolean result of authorizing
     */
    public boolean work() {
        try {
            String currentUsername;
            String currentPassword;
            boolean out = false;
            while (true) {
                if (out) {
                    break;
                }
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                System.out.println("Waiting data for user`s authorizing...");
                datagramSocket.receive(packet);
                String auth = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Message [" + auth + "] is received from client.");
                String[] parsedAuth = auth.trim().split(" ", 3);
                enteredUsername = parsedAuth[1];
                boolean statusOfCommand;
                switch (parsedAuth[0]) {
                    case "login":
                        System.out.println("in case");
                        statusOfCommand = databaseManager.login(parsedAuth[1], parsedAuth[2]);
                        if (!statusOfCommand) {
                            sendMessage("false", "localhost", 4241);
                            System.out.println("sent");
                            break;
                        } else {
                            currentUsername = parsedAuth[1];
                            currentPassword = parsedAuth[2];
                            sendMessage("true", "localhost", 4241);
                            out = true;
                            System.out.println("User" + currentUsername + "connected successfully.");
                            break;
                        }

                    case "register":
                        statusOfCommand = databaseManager.register(parsedAuth[1]);
                        if (statusOfCommand) {
                            databaseManager.login(parsedAuth[1], parsedAuth[2]);
                            currentUsername = parsedAuth[1];
                            currentPassword = parsedAuth[2];
                            sendMessage("true", "localhost", 4241);
                            System.out.println("User" + currentUsername + "connected successfully.");
                            out = true;
                            break;
                        } else {
                            sendMessage("false", "localhost", 4241);
                            break;
                        }
                    default:
                        break;
                }
                if (!packet.getData().equals("false")) {
                    out = true
                } else packet.f
            }
            
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //System.out.println("User" + currentUsername + "connected successfully.");
            while (true) {
                Connection connection = DriverManager.getConnection(URL, username, password);
                DatagramPacket packetIn = new DatagramPacket(bufIn, bufIn.length);
                datagramSocket.receive(packetIn);
                InetAddress address = packetIn.getAddress();
                int port = packetIn.getPort();
                //packet = new DatagramPacket(buf, buf.length, address, port);
                String command = new String(packetIn.getData(), 0, packetIn.getLength());
                System.out.println("Message [" + command + "] is received from client.");
                String[] parsedCommand = command.trim().split(" ", 2);
                String answer = new String();
                switch (parsedCommand[0]) {
                    case "add":
                        answer = new AddCommand(this, databaseManager, true).execute(parsedCommand[1]);
                        break;
                    case "add_if_min":
                        answer = new AddIfMinCommand(databaseManager).execute(parsedCommand[1]);
                        break;
                    case "clear":
                        answer = new ClearCommand(this, databaseManager).execute();
                        break;
                    case "count_greater_than_nationality":
                        answer = new CountGreaterThanNationalityCommand().execute(parsedCommand[1]);
                        break;
                    case "execute_script":
                        answer = new ExecuteScriptCommand(this, databaseManager).execute(parsedCommand[1]);
                        break;
                    case "exit":
                        answer = new ExitCommand().execute();
                        break;
                    case "group_counting_by_nationality":
                        answer = new GroupCountingByNationalityCommand().execute();
                        break;
                    case "help":
                        answer = new HelpCommand().execute();
                        break;
                    case "info":
                        answer = new InfoCommand(this).execute();
                        break;
                    case "register":
                        //answer = new RegisterCommand(databaseManager).execute();
                        break;
                    case "remove_by_id_command":
                        answer = new RemoveByIdCommand(databaseManager).execute(parsedCommand[1]);
                        break;
                    case "remove_greater":
                        answer = new RemoveGreaterCommand(this, databaseManager).execute(parsedCommand[1]);
                        break;
                    case "remove_lower":
                        answer = new RemoveLowerCommand(this, databaseManager).execute(parsedCommand[1]);
                        break;
                    case "show":
                        answer = new ShowCommand(this).execute();
                        break;
                    case "sum_of_height":
                        answer = new SumOfHeightCommand().execute();
                        break;
                    case "update_by_id":
                        answer = new UpdateByIdCommand(databaseManager).execute(parsedCommand[1]);
                        break;
                    default:
                        answer = "Unknown command. Try again.";
                        break;
                }
                bufOut = answer.getBytes();
                DatagramPacket sendingPacket = new DatagramPacket(bufOut, bufOut.length, address, port);
                String check = new String(sendingPacket.getData(), 0, sendingPacket.getLength());
                datagramSocket.send(sendingPacket);
                System.out.println("Answer has been recent successfully. Content of answer: ");
                System.out.println(check);
            }
        } catch (SQLException sqlException) {
            System.out.println("Database is not available at the moment. Try later.");
            return false;
        } catch (IOException ioException) {
            System.out.println("Data manipulations exception.");
            return false;
        }
    }

    public void sendMessage(String message, String address, int port) {
        try {
            byte[] byteAnswer = message.getBytes();
            InetAddress adr = InetAddress.getByName(address);
            DatagramPacket packetForMessage = new DatagramPacket(byteAnswer, byteAnswer.length, adr, port);
            datagramSocket.send(packetForMessage);
            System.out.println("really sent");
        } catch (IOException ioException) {
            System.out.println("Server error. Try later.");
            System.exit(1);
        }
    }

    public int getId() {
        return id;
    }

    public String getEnteredUsername() {
        return enteredUsername;
    }

}
