import java.io.IOException;
import java.net.*;
import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.*;


public class ClientConnection {

    boolean access = false;

    public ClientConnection() {
    }

    public boolean connect() {
        System.out.println("Application was started!\nFor using collection in database you should log in or register." +
                "\nWrite [register username password] for registration or [login username password] for log in.");
        try {
            InetAddress address = InetAddress.getByName("localhost");
            DatagramSocket datagramSocket = new DatagramSocket();
            while (true) {
                if (access) {
                    return true;
                }
                System.out.println("You should register or log in now. Also you can write [exit] for switching off this application.");
                Scanner scanner = new Scanner(System.in);
                String enter = scanner.nextLine().replaceAll("\\s+", " ");
                String[] enteredRequest = enter.trim().toLowerCase(Locale.ROOT).split(" ", 3);
                switch (enteredRequest[0]) {
                    case "exit":
                        System.out.println("Program will be finished now.");
                        System.exit(0);
                        break;
                    case "login":
                        if (enteredRequest.length != 3) {
                            System.out.println("You should you syntax of command like in example. Please try again.");
                        } else {
                            byte[] contentOfPacket = new StringBuilder().append(enteredRequest[0]).append(" ")
                                    .append(enteredRequest[1]).append(" ")
                                    .append(enteredRequest[2]).toString().getBytes();
                            DatagramPacket packet = new DatagramPacket(contentOfPacket, contentOfPacket.length, address, 4241);
                            datagramSocket.send(packet);
                            byte[] content = new byte[65535];
                            DatagramPacket packetWithAnswer = new DatagramPacket(content, content.length);
                            System.out.println("waiting");
                            datagramSocket.receive(packetWithAnswer);
                            System.out.println("received");
                            String answer = new String(packetWithAnswer.getData(), 0, packetWithAnswer.getLength());
                            System.out.println("yeah");
                            if (answer.equals("true")) {
                                System.out.println("You are connected to database successfully. You can enter a command now.");
                                access = true;
                            } else {
                                System.out.println("Username or password are wrong. Try later.");
                            }
                        }
                        break;
                    case "register":
                        if (enteredRequest.length != 3) {
                            System.out.println("You should you syntax of command like in example. Please try again.");
                        } else {
                            byte[] contentOfPacket2 = new StringBuilder().append(enteredRequest[0]).append(" ")
                                    .append(enteredRequest[1]).append(" ")
                                    .append(enteredRequest[2]).toString().getBytes();
                            DatagramPacket packet2 = new DatagramPacket(contentOfPacket2, contentOfPacket2.length, address, 4242);
                            datagramSocket.send(packet2);
                            byte[] content2 = new byte[65535];
                            DatagramPacket packetWithAnswer2 = new DatagramPacket(content2, contentOfPacket2.length);
                            datagramSocket.receive(packetWithAnswer2);
                            String answer2 = new String(packetWithAnswer2.getData(), 0, packetWithAnswer2.getLength());
                            if (answer2.equals("true")) {
                                System.out.println("You are registered to database successfully. You can log in now.");
                                access = true;
                            } else {
                                System.out.println("Registration is not available at the moment. Please try later.");
                            }
                        }
                        break;
                    default:
                        System.out.println("Unknown command. You should register or login.");
                        break;
                }
            }
        } catch (UnknownHostException unknownHostException) {
            System.err.println("Host is not available at the moment. Please try later.");
            return false;
        } catch (SocketException socketException) {
            System.err.println("Socket is not available at the moment. Please try later.");
            return false;
        } catch (IOException ioException) {
            System.err.println("Data sending is not available now. Please try later.");
            return false;
        }
    }

    public void work() {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            System.out.println("You can write a command.");
            Scanner fromKeyboard = new Scanner(System.in);
            while (true) {
                String command = fromKeyboard.nextLine();
                String[] parsedCommand = command.trim().split(" ", 3);
                if (parsedCommand[0].equals("add")) {
                    Receiver receiver = new Receiver();
                    Person person = new Person(receiver.receiveId(), receiver.receiveName(), receiver.receiveCoordinates(),
                            ZonedDateTime.now().toString(), receiver.receiveHeight(), receiver.receiveEyeColor(),
                            receiver.receiveHairColor(), receiver.receiveNationality(), receiver.receiveLocation());
                    StringBuilder result = new StringBuilder();
                    XmlMapper mapper = new XmlMapper();
                    result.append("add").append(" ").append(mapper.writeValueAsString(person));
                    byte[] buf = result.toString().getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4242);
                    socket.send(packet);
                } else if (parsedCommand[0].equals("update_by_id")) {
                    Receiver receiver = new Receiver();
                    int id;
                    for (; ; ) {
                        try {
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Enter ID of element.");
                            id = scanner.nextInt();
                            if (id > 0) {
                                break;
                            } else {
                                System.out.println("ID must be a positive number. Try again.");
                            }
                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println("Value must be a number less than 2 billion and positive.");
                        } catch (NoSuchElementException noSuchElementException) {
                            System.out.println("Program will be finished now.");
                            System.exit(0);
                        }
                    }
                    Person person = new Person(id, receiver.receiveName(), receiver.receiveCoordinates(),
                            ZonedDateTime.now().toString(), receiver.receiveHeight(), receiver.receiveEyeColor(),
                            receiver.receiveHairColor(), receiver.receiveNationality(), receiver.receiveLocation());
                    StringBuilder result = new StringBuilder();
                    XmlMapper mapper = new XmlMapper();
                    result.append("update_by_id").append(" ").append(mapper.writeValueAsString(person));
                    byte[] buf = result.toString().getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4242);
                    socket.send(packet);
                } else if (parsedCommand[0].equals("add_if_min")) {
                    Receiver receiver = new Receiver();
                    Person person = new Person(receiver.receiveId(), receiver.receiveName(), receiver.receiveCoordinates(),
                            ZonedDateTime.now().toString(), receiver.receiveHeight(), receiver.receiveEyeColor(),
                            receiver.receiveHairColor(), receiver.receiveNationality(), receiver.receiveLocation());
                    StringBuilder result = new StringBuilder();
                    XmlMapper mapper = new XmlMapper();
                    result.append("add_if_min").append(" ").append(mapper.writeValueAsString(person));
                    byte[] buf = result.toString().getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4242);
                    socket.send(packet);
                } else if (parsedCommand[0].equals("exit")) {
                    byte[] buf = command.getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4242);
                    socket.send(packet);
                    System.out.println("Finishing a program");
                    System.exit(0);
                } else {
                    byte[] buf = command.getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4242);
                    socket.send(packet);
                }
                byte[] bufOut = new byte[65535];
                DatagramPacket fromServer = new DatagramPacket(bufOut, bufOut.length);
                socket.receive(fromServer);
                String answer = new String(fromServer.getData(), 0, fromServer.getLength());
                System.out.println(answer);
            }
        } catch (IOException exception) {
            System.err.println("Server is not available at the moment. Reconnect? (enter {yes} or {no})?");
            String answer;
            while (!(answer = new Scanner(System.in).nextLine()).equals("yes")) {
                switch (answer) {
                    case "":
                        break;
                    case "no":
                        exit();
                        break;
                    default:
                        System.out.println("Please write a correct answer.");
                }
            }
            System.out.print("Connecting...");
        } catch (NoSuchElementException | ArrayIndexOutOfBoundsException exception) {
            System.out.println("Program will be finished now.");
            System.exit(0);
        }
    }

    /**
     * Method which allows to switch off a server
     */
    private void exit() {
        System.out.println("Finishing a program.");
        System.exit(0);
    }
}