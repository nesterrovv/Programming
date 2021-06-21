package commands;

import data.Person;
import serverCode.DatabaseManager;
import serverCode.ServerConnection;
import serverCode.CollectionManager;
import serverCode.UnknownUserException;
import java.util.Set;

public class RemoveGreaterCommand extends AbstractCommand {

    private final ServerConnection serverConnection;
    private final DatabaseManager databaseManager;

    public RemoveGreaterCommand(ServerConnection connection, DatabaseManager databaseManager) {
        this.serverConnection = connection;
        this.databaseManager = databaseManager;
        setDescription("Removes all elements which height more than current.");
        try {
            if (!access) throw new UnknownUserException("You need to login or register before executing commands.");
        } catch (UnknownUserException unknownUserException) {
            unknownUserException.printStackTrace();
        }
    }

    public synchronized String execute(String arg) {
        CollectionManager manager = CollectionManager.getInstance();
        long height = Long.valueOf(arg);
        Set<Person> persons = CollectionManager.getInstance().getPersons();
        int counter = 0;
        for (Person person : persons) {
            if (person.getHeight() > height) {
                if (databaseManager.removeElement(person)) {
                    persons.remove(person);
                    counter += 1;
                }
            }
        }
        return "Operation was finished successfully. " + counter + " elements were deleted.";
    }
}