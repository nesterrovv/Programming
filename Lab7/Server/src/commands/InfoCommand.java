package commands;

import data.Person;
import serverCode.DatabaseManager;
import serverCode.ServerConnection;
import serverCode.CollectionManager;
import java.util.Set;

public class InfoCommand extends AbstractCommand {

    ServerConnection serverConnection;
    private final String owner;
    private final DatabaseManager databaseManager;

    public InfoCommand(ServerConnection connection, String owner, DatabaseManager databaseManager) {
        setDescription("Prints information about the collection.");
        this.serverConnection = connection;
        this.owner = owner;
        this.databaseManager = databaseManager;
    }

    @Override
    public synchronized String execute() {
        CollectionManager manager = CollectionManager.getInstance();
        manager.load(databaseManager);
        Set<Person> collection = manager.getPersons();
        int amountOfElements = 0;
        for (Person p: collection) if (p.getOwner().equals(owner)) amountOfElements++;
        return CollectionManager.getInstance().toString() + "\nYour name in system is: " +
                owner + "\nTotal amount of yours elements: " + amountOfElements;

    }
}