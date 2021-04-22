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
                        System.out.println("Завершение программы.");
                    }
                } catch (IOException e) {
                    System.err.println("Нет связи с сервером. Подключться ещё раз (введите {да} или {нет})?");
                    String answer;
                    while (!(answer = fromKeyboard.nextLine()).equals("да")) {
                        switch (answer) {
                            case "":
                                break;
                            case "нет":
                                exit();
                                break;
                            default: System.out.println("Введите корректный ответ.");
                        }
                    }
                    System.out.print("Подключение ...");
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
                            System.out.println("Файл по указанному пути не найден.");
                        } catch (SecurityException e) {
                            System.out.println("Файл защищён от чтения.");
                        } catch (IOException e) {
                            System.out.println("Что-то не так с файлом.");
                        }
                        break;
                    default:
                        toServer.writeObject(command);
                        System.out.println((String) fromServer.readObject());
                }
            }
            exit();
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден: " + e.getMessage());
        }
    }

    private String importingFile(String path) throws SecurityException, IOException {
        File localCollection = new File(path);
        String extension = localCollection.getAbsolutePath().substring(localCollection.getAbsolutePath().lastIndexOf(".") + 1);
        if (!localCollection.exists() | localCollection.length() == 0  | !extension.equals("json"))
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
        System.out.println("Завершение программы.");
        System.exit(0);
    }
}