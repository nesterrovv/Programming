package client;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import server.data.Person;
import server.serverCode.CollectionManager;

import java.io.*;
import java.net.*;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientConnection {

    public ClientConnection() {}

    private Scanner fromKeyboard;
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf;

    public void work() {
        try {
            socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            //byte[] buf = new byte[65535];
            fromKeyboard = new Scanner(System.in);
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
                    buf = result.toString().getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4242);
                    socket.send(packet);
                }
                if (parsedCommand[0].equals("add_if_min")) {
                    Receiver receiver = new Receiver();
                    Person person = new Person(receiver.receiveId(), receiver.receiveName(), receiver.receiveCoordinates(),
                            ZonedDateTime.now().toString(), receiver.receiveHeight(), receiver.receiveEyeColor(),
                            receiver.receiveHairColor(), receiver.receiveNationality(), receiver.receiveLocation());
                    StringBuilder result = new StringBuilder();
                    XmlMapper mapper = new XmlMapper();
                    result.append("add_if_min").append(" ").append(mapper.writeValueAsString(person));
                    buf = result.toString().getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4242);
                    socket.send(packet);
                }
                if (parsedCommand[0].equals("exit")) {
                    buf = command.getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4242);
                    socket.send(packet);
                    System.out.println("Finishing a program");
                    System.exit(0);
                } else {
                    buf = command.getBytes();
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
        } catch (NoSuchElementException | ArrayIndexOutOfBoundsException exception) {
            System.out.println("Program will be finished now.");
            System.exit(0);
        }
    }

    private void exit() {
        System.out.println("Finishing a program.");
        System.exit(0);
    }
}