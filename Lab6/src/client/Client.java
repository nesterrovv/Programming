import data.Person;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    /** Collection manager for realising user`s commands */
    private final CollectionManager collectionManager;
    /** Field for receiving user`s command */
    private String userCommand;
    /** Field for separating user input into a command and an argument to it */
    private static String[] finalUserCommand;

    {
        userCommand = "";
    }

    public Client(CollectionManager manager) {
        this.collectionManager = manager;
        try {
            clientSocket = new Socket("localhost", 4242);
            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException ioException) {
            System.out.println("Server is not available. Reconnection...");
        }
    }

    public void interactiveMod() {
        try {
            try {
                while (!userCommand.equals("exit")) {
                    System.out.print("Enter a command: ");
                    userCommand = reader.readLine();
                    finalUserCommand = userCommand.trim().toLowerCase().split(" ", 2);
                    try {
                        switch (finalUserCommand[0]) {
                            case "":
                                break;
                            case "help":
                                out.write("help" + "\n");
                                out.flush();
                                break;
                            case "info":
                                out.write("info" + "\n");
                                out.flush();
                                break;
                            case "show":
                                out.write("show" + "\n");
                                out.flush();
                                break;
                            case "add":
                                out.write("add" + "\n");
                                out.flush();
                                break;
                            case "update_by_id":
                                out.write("update_by_id" + finalUserCommand[1].toString() + "\n");
                                out.flush();
                                break;
                            case "remove_by_id":
                                out.write("remove_by_id" + finalUserCommand[1].toString() + "\n");
                                out.flush();
                                break;
                            case "clear":
                                out.write("clear" + "\n");
                                out.flush();
                                break;
                            case "save":
                                System.out.println("Saving is an automatic process.");
                                break;
                            case "execute_script":
                                collectionManager.execute_script(finalUserCommand[1]);
                                break;
                            case "exit":
                                collectionManager.exit();
                                break;
                            case "add_if_min":
                                System.out.println("Enter characteristics of element, which will be compared with elements in collection.");
                                collectionManager.add_if_min(new Person(collectionManager.receiveId(), collectionManager.receiveName(),
                                        collectionManager.receiveCoordinates(), collectionManager.returnDate(),
                                        collectionManager.receiveHeight(), collectionManager.receiveEyeColor(),
                                        collectionManager.receiveHairColor(), collectionManager.receiveNationality(),
                                        collectionManager.receiveLocation()));
                                break;
                            case "remove_greater":
                                System.out.println("Enter characteristics of element, which will be compared with elements in collection.");
                                collectionManager.remove_greater(collectionManager.receiveHeight());
                                break;
                            case "remove_lower":
                                System.out.println("Enter characteristics of element, which will be compared with elements in collection.");
                                collectionManager.remove_lower(collectionManager.receiveHeight());
                                break;
                            case "sum_of_height":
                                collectionManager.sum_of_height();
                                break;
                            case "group_counting_by_nationality":
                                collectionManager.group_counting_by_nationality();
                                break;
                            case "count_greater_than_nationality":
                                System.out.println("Enter nationality, which will be compared with element`s nationality.");
                                collectionManager.count_greater_than_nationality(collectionManager.receiveNationality());
                                break;
                            default:
                                System.out.println("Unknown command. Write help for help.");
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Argument of command is absent. Write help for help.");
                    }
                }
            } catch (IOException ioException) {
                System.out.println("Input output exception");
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Program will be finished now.");
            System.exit(1);
        }
    }

}