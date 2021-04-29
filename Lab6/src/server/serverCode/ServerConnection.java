package server.serverCode;

import server.commands.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Objects;

public class ServerConnection implements Runnable {

    private final CollectionManager serverCollection;
    private final Socket incoming;
    private final HashMap<String, AbstractCommand> availableCommands;

    ServerConnection(CollectionManager serverCollection, Socket incoming) {
        this.serverCollection = serverCollection;
        this.incoming = incoming;
        availableCommands = new HashMap<>();
        availableCommands.put("add", new AddCommand(serverCollection));
        availableCommands.put("add_if_min", new AddIfMinCommand(serverCollection));
        availableCommands.put("clear", new ClearCommand(serverCollection));
        availableCommands.put("count_greater_than_nationality", new CountGreaterThanNationalityCommand(serverCollection));
        availableCommands.put("execute_script", new ExecuteScriptCommand(serverCollection));
        availableCommands.put("exit", new ExitCommand(serverCollection));
        availableCommands.put("group_counting_by_nationality", new GroupCountingByNationalityCommand(serverCollection));
        availableCommands.put("help", new HelpCommand(serverCollection));
        availableCommands.put("info", new InfoCommand(serverCollection));
        availableCommands.put("remove_by_id", new RemoveByIdCommand(serverCollection));
        availableCommands.put("remove_greater", new RemoveGreaterCommand(serverCollection));
        availableCommands.put("remove_lower", new RemoveLowerCommand(serverCollection));
        availableCommands.put("save", new SaveCommand(serverCollection));
        availableCommands.put("show", new ShowCommand(serverCollection));
        availableCommands.put("sum_of_height", new SumOfHeightCommand(serverCollection));
        availableCommands.put("update_by_id", new UpdateByIdCommand(serverCollection));
    }

    @Override
    public void run() {
        try (ObjectInputStream getFromClient = new ObjectInputStream(incoming.getInputStream());
             ObjectOutputStream sendToClient = new ObjectOutputStream(incoming.getOutputStream())) {
            sendToClient.writeObject("The connection has been established.\nYou can write a command. ");
            AbstractCommand errorCommand = new AbstractCommand(null) {
                @Override
                public String execute() {
                    return "Unknown command. Write help for receiving list of available commands.";
                }
            };
            while (true) {
                try {
                    String requestFromClient = (String) getFromClient.readObject();
                    System.out.print("Получено [" + requestFromClient + "] от " + incoming + ". ");
                    String[] parsedCommand = requestFromClient.trim().split(" ",2);
                    if (parsedCommand.length == 1)
                        sendToClient.writeObject(availableCommands.getOrDefault(parsedCommand[0], errorCommand).execute());
                    else if (parsedCommand.length == 2)
                        sendToClient.writeObject(availableCommands.getOrDefault(parsedCommand[0], errorCommand).execute(parsedCommand[1]));
                    System.out.println("Answer has been sent successfully.");
                } catch (SocketException e) {
                    System.out.println(incoming + " its disconnected to server."); //EcepWindows
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(incoming + " is disconnected to server."); //Unix
        }
    }

    @Override
    public String toString() {
        return "ServerConnection{" +
                "serverCollection=" + serverCollection +
                ", incoming=" + incoming +
                ", availableCommands=" + availableCommands +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerConnection)) return false;
        ServerConnection that = (ServerConnection) o;
        return Objects.equals(serverCollection, that.serverCollection) &&
                Objects.equals(availableCommands, that.availableCommands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverCollection, availableCommands);
    }
}