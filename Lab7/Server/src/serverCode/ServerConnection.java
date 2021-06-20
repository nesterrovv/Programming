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
        this.URL = "jdbc:postgresql://localhost:4242/studs";
        this.username = "s312621";
        this.password = "ctj483";
        databaseManager = new DatabaseManager(URL, username, password);
    }

    /**
     * Method for authorizing user
     * @return boolean result of authorizing
     */
    public boolean authorize() {
        try {
            datagramSocket = new DatagramSocket(4241);
            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                System.out.println("Waiting data for user`s authorizing...");
                datagramSocket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String command = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Message [" + command + "] is received from client.");
                String[] parsedCommand = command.trim().split(" ", 3);
                enteredUsername = parsedCommand[1];
                boolean statusOfCommand;
                //String result;
                    switch (parsedCommand[0]) {
                        case "login":
                            statusOfCommand = databaseManager.login(parsedCommand[1], parsedCommand[2]);
                            if (statusOfCommand) return true;
                            else continue;
                            //break;
                        case "register":
                            statusOfCommand = databaseManager.register(parsedCommand[1]);
                            if (statusOfCommand) {
                                databaseManager.login(parsedCommand[1], parsedCommand[2]);
                                return true;
                            }
                            break;
                        default:
                            break;
                    }
                }
        } catch (SocketException socketException) {
            System.out.println("Socket exception");
            return false;
        } catch (IOException ioException) {
            System.out.println("IO exception");
            return false;
        }
    }

    /**
     * Method for command executes,allows to change collection into database
     * @param username name of user which works with collection
     */
    public void work(String username) {

        try {
            //DatagramSocket socket = new DatagramSocket(4242);
            if (this.authorize()) { // if authorizing was success
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
            }
        } catch (IOException exception) {
            System.err.println("User is disconnected to server.");
        } catch (NoSuchElementException | ArrayIndexOutOfBoundsException exception) {
            System.out.println("Program will be finished now.");
            System.exit(0);
        } catch(SQLException sqlException) {
            System.out.println("Server is not available at the moment. Please try later.");
        }
    }

    public int getId() {
        return id;
    }

    public String getEnteredUsername() {
        return enteredUsername;
    }

}
