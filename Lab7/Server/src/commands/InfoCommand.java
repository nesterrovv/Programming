package commands;

import data.Person;
import serverCode.ServerConnection;
import serverCode.CollectionManager;
import serverCode.UnknownUserException;

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
        //int absoluteAmount = collection.size();
        int amountOfElements = 0;
        for (Person p: collection) if (p.getOwner().equals(serverConnection.getId())) amountOfElements++;
        return CollectionManager.getInstance().toString() + "\nYour name in system is: " +
                serverConnection.getId() + "\nAmount of yours elements: " + amountOfElements;
    }
}