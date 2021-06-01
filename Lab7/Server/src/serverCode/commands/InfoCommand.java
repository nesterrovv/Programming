package serverCode.commands;

import data.Person;
import serverCode.ServerConnection;
import serverCode.managers.CollectionManager;
import java.util.Set;

public class InfoCommand extends AbstractCommand {

    ServerConnection serverConnection;

    public InfoCommand(ServerConnection connection) {
        setDescription("Prints information about the collection.");
        this.serverConnection = connection;
    }

    @Override
    public synchronized String execute() {
        Set<Person> collection = CollectionManager.getInstance().getPersons();
        int amountOfElements = 0;
        for (Person p: collection) if (p.getId() == serverConnection.getId()) amountOfElements++;
        return CollectionManager.getInstance().toString() + "\nYour ID in system is: " +
                serverConnection.getId() + "\nAmount of yours elements: " + amountOfElements;
    }
}