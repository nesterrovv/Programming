package serverCode;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import commands.*;

public class ServerConnection {

    String URL; // URL of Postgres database on port 4242
    String username; // username for connection to Helios
    String password; // password for connection to Helios
    DatagramSocket datagramSocket; // socket for connection
    boolean permission_to_work; // boolean flag which allow to start method run for using commands after verification
    byte[] bufIn = new byte[60000]; // array for receiving package with command from user
    byte bufOut[] = new byte[60000]; // array for sending an answer (result of command execution) to client
    DatabaseManager databaseManager;
    private String id;
    String enteredUsername;
    int check;

    public ServerConnection() {
        try {
            this.URL = "jdbc:postgresql://localhost:4242/studs";
            this.username = "s312621";
            this.password = "ctj483";
            databaseManager = new DatabaseManager(URL, username, password);
            datagramSocket = new DatagramSocket(4242);
        } catch (SocketException socketException) {
            System.out.println("Socket is not available.");
        }
    }

    /**
     * Method for authorizing user
     * @return boolean result of authorizing
     */
    public boolean work() {
        String answer; // message for sending to client (answer0 + answer1)
        String answer0 = new String(); // message for authorizing result
        String answer1 = new String(); // message for command execution result
        try {
            while (true) {
                //Connection connection = DriverManager.getConnection(URL, username, password);
                DatagramPacket packetIn = new DatagramPacket(bufIn, bufIn.length);
                datagramSocket.receive(packetIn);
                InetAddress address = packetIn.getAddress();
                int port = packetIn.getPort();
                String command = new String(packetIn.getData(), 0, packetIn.getLength());
                System.out.println("Message [" + command + "] is received from client.");
                String[] parsedCommand = command.trim().split(" ");
                //String answer0 = new String();
                if (parsedCommand[0].equals("login") || parsedCommand[0].equals("register")) {
                    if (parsedCommand[0].equals("login")) {
                        boolean res = databaseManager.login(parsedCommand[1], parsedCommand[2]);
                        if (res) {
                            permission_to_work = true;
                            id = parsedCommand[1];
                            answer0 = "Good! Write a command here.";
                        } else answer0 = "Login or password are wrong.";
                    } else {
                        boolean res = databaseManager.register(parsedCommand[1], parsedCommand[2]);
                        if (res) {
                            permission_to_work = true;
                            id = parsedCommand[1];
                            answer0 = "Good! Write a command here.";
                        } else answer0 = "User is already exist. You can try to log in.";
                    }
                } else {
                        if (permission_to_work) {
                            switch (parsedCommand[0]) {
                                case "add":
                                    answer1 = new AddCommand(this, databaseManager, true).execute(new StringBuilder(parsedCommand[1]).append(" ").append(parsedCommand[2]).toString());
                                    break;
                                case "add_if_min":
                                    answer1 = new AddIfMinCommand(databaseManager).execute(new StringBuilder(parsedCommand[1]).append(" ").append(parsedCommand[2]).toString());
                                    break;
                                case "clear":
                                    answer1 = new ClearCommand(this, databaseManager).execute();
                                    break;
                                case "count_greater_than_nationality":
                                    answer1 = new CountGreaterThanNationalityCommand().execute(parsedCommand[1]);
                                    break;
                                case "execute_script":
                                    answer1 = new ExecuteScriptCommand(this, databaseManager).execute(parsedCommand[1]);
                                    break;
                                case "exit":
                                    answer1 = new ExitCommand().execute();
                                    System.exit(0);
                                    break;
                                case "group_counting_by_nationality":
                                    answer1 = new GroupCountingByNationalityCommand().execute();
                                    break;
                                case "help":
                                    answer1 = new HelpCommand().execute();
                                    break;
                                case "info":
                                    answer1 = new InfoCommand(this).execute();
                                    break;
                                case "remove_by_id_command":
                                    answer1 = new RemoveByIdCommand(databaseManager, this).execute(parsedCommand[1]);
                                    break;
                                case "remove_greater":
                                    answer1 = new RemoveGreaterCommand(this, databaseManager).execute(parsedCommand[1]);
                                    break;
                                case "remove_lower":
                                    answer1 = new RemoveLowerCommand(this, databaseManager).execute(parsedCommand[1]);
                                    break;
                                case "show":
                                    answer1 = new ShowCommand(this).execute();
                                    break;
                                case "sum_of_height":
                                    answer1 = new SumOfHeightCommand().execute();
                                    break;
                                case "update_by_id":
                                    answer1 = new UpdateByIdCommand(databaseManager, this).execute(new StringBuilder(parsedCommand[1]).append(" ").append(parsedCommand[2]).toString());
                                    break;
                                default:
                                    answer1 = "Unknown command. Try again.";
                                    break;
                            }
                        } else answer1 = "You must log in or register before executing some commands.";
                    }
                answer = new StringBuilder(answer0).append("\n").append(answer1).toString();
                bufOut = answer.getBytes();
                DatagramPacket sendingPacket = new DatagramPacket(bufOut, bufOut.length, address, port);
                String check = new String(sendingPacket.getData(), 0, sendingPacket.getLength());
                datagramSocket.send(sendingPacket);
                System.out.println("Answer has been recent successfully. Content of answer: ");
                System.out.println(check);
            }
        } catch (IOException ioException) {
            System.out.println("Data manipulations exception.");
            return false;
        }
    }

    public String getId() {
        return id.toString();
    }

    public String getEnteredUsername() {
        return enteredUsername;
    }

}
