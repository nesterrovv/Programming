package commands;

import data.Person;
import serverCode.DatabaseManager;
import serverCode.ServerConnection;
import serverCode.CollectionManager;
import serverCode.UnknownUserException;

import java.util.Set;

public class RemoveLowerCommand extends AbstractCommand {

    private final ServerConnection serverConnection;
    private final DatabaseManager databaseManager;

    public RemoveLowerCommand(ServerConnection connection, DatabaseManager databaseManager) {
        this.serverConnection = connection;
        this.databaseManager = databaseManager;
        setDescription("Removes all elements which height more than current.");
    }

    public synchronized String execute(String arg) {
        CollectionManager manager = CollectionManager.getInstance();
        long height = Long.valueOf(arg);
        Set<Person> persons = CollectionManager.getInstance().getPersons();
        int counter = 0;
        for (Person person : persons) {
            if (person.getHeight() < height && (person.getOwner().equals(serverConnection.getId()))) {
                if (databaseManager.removeElement(person)) {
                    persons.remove(person);
                    counter += 1;
                }
            } else return "You cannot remove this element therefore you are not owner of this element.";
        }
        return "Operation was finished successfully. " + counter + " elements were deleted.";
    }
}