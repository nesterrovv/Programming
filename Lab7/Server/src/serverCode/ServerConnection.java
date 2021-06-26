package serverCode;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.sun.security.ntlm.Server;
import commands.*;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class ServerConnection {

    /** URL of studs database on PostgreSQL into Helios server */
    String URL;
    /** Admin username for connection to Helios */
    String username;
    /** Admin password for connection to Helios */
    String password;
    /** Socket for connection to Helios and receiving/sending some data */
    DatagramSocket datagramSocket;
    /** Byte array for receiving a command from client */
    byte[] bufferForCommand = new byte[60000];
    /** Byte array for sending answer to client */
    byte[] bufferForAnswer = new byte[60000];
    /** Class for manipulation with database */
    DatabaseManager databaseManager;
    /** String for saving username for adding to database before some changes by this user */
    String owner;
    /** Hex string for saving hex of password. This information will be added to users database */
    String hexPassword;
    /** Class for hashing data */
    Cryptographer cryptographer = new Cryptographer();

    InetAddress address;
    int port;

    ExecutorService pool = Executors.newCachedThreadPool();
    ReentrantLock locker = new ReentrantLock();

    /** Constructor of this class which allows to check if data in properties file is correct.
     * Gives possibility to connect to database and start command executing
     */
    public ServerConnection() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("helios_db.properties");
            Properties properties = System.getProperties();
            properties.load(inputStream);
            URL = properties.getProperty("url_address");
            if (cryptographer.encrypt(properties.getProperty("user")).equals("94e4c8545d5f7092b4a6b943303998fd38b19d" +
                    "db72882eb4fdb8819b3739df18ef4786551a9a56d27abf721334e43592fcd4c5e32d869524b8a2ab1ba4f015a1")) {
                username = properties.getProperty("user");

            } else throw new IOException();
            if (cryptographer.encrypt(properties.getProperty("password")).equals("764b34754e504846860e6619c782238390" +
                    "73a5a1110be726d6b6873cb002484e6385c1c78d70ed0623cfbcf772ba10612b27ef0b957728c38a7e3f6c9a15aa66")) {
                password = properties.getProperty("password");
                hexPassword = cryptographer.encrypt(properties.getProperty("password"));

            }
            if (username != null && password != null ) {
                System.out.println("Data for connection is correct.");
            } else throw new NullPointerException();
            databaseManager = new DatabaseManager(URL, username, password);
            datagramSocket = new DatagramSocket(4242);
        } catch (SocketException socketException) {
            System.out.println("Socket is not available.");he
            System.exit(1);
        } catch (IOException | NullPointerException exception) {
            System.out.println("Invalid data into properties file. Cannot resolve connection to database.\n" +
                    "Check properties file and try again.");
            System.exit(1);
        }
    }


    /** Method for executing commands from user */
    public void work() {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Object> outPut = service.submit(() -> {
            String answer = new String();
            synchronized (ServerConnection.class) {
                boolean permission = false;
                System.out.println("work");
                while (true) {
                    try {
                        DatagramPacket packetForCommand = new DatagramPacket(bufferForCommand, bufferForCommand.length);
                        datagramSocket.receive(packetForCommand);
                        address = packetForCommand.getAddress();
                        port = packetForCommand.getPort();
                        String command = new String(packetForCommand.getData(), 0, packetForCommand.getLength());
                        System.out.println("Message [" + command + "] is received from client for executing.");
                        DatagramPacket finalPacket = packetForCommand; // temp packet for reading thread.
                        Future<String> readingResult = pool.submit(() ->
                                new String(finalPacket.getData(), 0, finalPacket.getLength()));
                        String received = null;
                        locker.lock();
                        try {
                            received = readingResult.get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                            System.out.println("Execution was interrupted.");
                            continue;
                        } finally {
                            locker.unlock();
                        }
                        String[] parsedCommand = command.trim().split(" ");
                        if (parsedCommand[0].equals("login") || parsedCommand[0].equals("register")) {
                            if (parsedCommand[0].equals("login")) {
                                boolean res = databaseManager.login(parsedCommand[1], parsedCommand[2]);
                                if (res) {
                                    owner = parsedCommand[1];
                                    hexPassword = cryptographer.encrypt(parsedCommand[2]);
                                    permission = true;
                                } else permission = false;
                            } else {
                                boolean res = databaseManager.register(parsedCommand[1], parsedCommand[2]);
                                if (res) {
                                    owner = parsedCommand[1];
                                    hexPassword = cryptographer.encrypt(parsedCommand[2]);
                                    permission = true;
                                } else permission = false;
                            }
                        } else {
                            if (permission) {
                                switch (parsedCommand[0]) {
                                    case "add":
                                        answer = new AddCommand(databaseManager, owner).execute(new StringBuilder(
                                                parsedCommand[1]).append(" ").append(parsedCommand[2]).toString());
                                        break;
                                    case "add_if_min":
                                        answer = new AddIfMinCommand(databaseManager, owner).execute(new StringBuilder(parsedCommand[1])
                                                .append(" ").append(parsedCommand[2]).toString());
                                        break;
                                    case "clear":
                                        answer = new ClearCommand(databaseManager, owner).execute();
                                        break;
                                    case "count_greater_than_nationality":
                                        answer = new CountGreaterThanNationalityCommand(databaseManager).execute(parsedCommand[1]);
                                        break;
                                    case "execute_script":
                                        answer = new ExecuteScriptCommand(this, databaseManager, owner).execute(parsedCommand[1]);
                                        break;
                                    case "exit":
                                        answer = new ExitCommand().execute();
                                        System.exit(0);
                                        break;
                                    case "group_counting_by_nationality":
                                        answer = new GroupCountingByNationalityCommand(databaseManager).execute();
                                        break;
                                    case "help":
                                        answer = new HelpCommand().execute();
                                        break;
                                    case "info":
                                        answer = new InfoCommand(this, owner, databaseManager).execute();
                                        break;
                                    case "remove_by_id_command":
                                        answer = new RemoveByIdCommand(databaseManager, owner).execute(parsedCommand[1]);
                                        break;
                                    case "remove_greater":
                                        answer = new RemoveGreaterCommand(databaseManager, owner).execute(parsedCommand[1]);
                                        break;
                                    case "remove_lower":
                                        answer = new RemoveLowerCommand(databaseManager, owner).execute(parsedCommand[1]);
                                        break;
                                    case "show":
                                        answer = new ShowCommand(owner, databaseManager).execute();
                                        break;
                                    case "sum_of_height":
                                        answer = new SumOfHeightCommand(databaseManager).execute();
                                        break;
                                    case "update_by_id":
                                        answer = new UpdateByIdCommand(databaseManager, owner).execute(new StringBuilder(
                                                parsedCommand[1]).append(" ").append(parsedCommand[2]).toString());
                                        break;
                                    default:
                                        answer = "Unknown command. Try again.";
                                        break;
                                }
                            } else answer = "You should log in or register before command executing.";
                        }
                        bufferForAnswer = answer.getBytes();
                        DatagramPacket sendingPacket = new DatagramPacket(bufferForAnswer, bufferForAnswer.length, address, port);
                        String check = new String(sendingPacket.getData(), 0, sendingPacket.getLength());
                        datagramSocket.send(sendingPacket);
                        System.out.println("Answer has been recent successfully. Content of answer: ");
                        System.out.println(check);
                    } catch (IOException ioException) {
                        try {
                            byte[] bufferWithException;
                            bufferWithException = "Server is not available at the moment. Try later.".getBytes();
                            DatagramPacket packetWithException = new DatagramPacket(bufferWithException, bufferWithException.length,
                                    address, port);
                            datagramSocket.send(packetWithException);
                        } catch (IOException ioException1) {
                            System.out.println("Data receiving error.");
                        }

                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        try {
                            byte[] bufferWithException;
                            bufferWithException = "You should write command with needable argument. Write help for help.".getBytes();
                            DatagramPacket packetWithException = new DatagramPacket(bufferWithException, bufferWithException.length,
                                    address, port);
                            datagramSocket.send(packetWithException);
                        } catch (IOException ioException1) {
                            System.out.println("Data receiving error.");
                        }
                    }
                }
            }
        });
    }
}