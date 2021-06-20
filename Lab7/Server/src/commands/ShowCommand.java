package commands;

import data.Person;
import serverCode.ServerConnection;
import serverCode.CollectionManager;
import serverCode.UnknownUserException;

import java.util.Set;

public class ShowCommand extends AbstractCommand {

    private final ServerConnection serverConnection;

    public ShowCommand(ServerConnection connection) {
        this.serverConnection = connection;
        setDescription("Prints all elements in string representation to standard output.");
        try {
            if (!access) throw new UnknownUserException("You need to login or register before executing commands.");
        } catch (UnknownUserException unknownUserException) {
            unknownUserException.printStackTrace();
        }
    }

    @Override
    public synchronized String execute() {
        CollectionManager manager = CollectionManager.getInstance();
        Set<Person> collection = manager.getPersons();
        StringBuilder result = new StringBuilder();
        if (collection.size() != 0) {
            for (Person p: collection) {
                result.append(p.toString());
                if (p.getId() == serverConnection.getId()) result.append(" YOU ARE OWNER");
                result.append("\n    ");
            }
            return result.toString();
        }
        else return "Коллекция пуста.";
    }
}