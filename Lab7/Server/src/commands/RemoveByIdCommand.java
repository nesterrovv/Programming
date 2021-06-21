package commands;

import data.Person;
import serverCode.CollectionManager;
import serverCode.DatabaseManager;
import serverCode.ServerConnection;
import serverCode.UnknownUserException;
import java.util.Set;

public class RemoveByIdCommand extends AbstractCommand {

    private final DatabaseManager databaseManager;
    private final ServerConnection connection;

    public RemoveByIdCommand(DatabaseManager databaseManager, ServerConnection connection) {
        setDescription("Removes element of the collection by it's id");
        this.databaseManager = databaseManager;
        this.connection = connection;
    }

    @Override
    public synchronized String execute(String arg) {
        CollectionManager manager = CollectionManager.getInstance();
        Set<Person> collection = manager.getPersons();
        int id = Integer.parseInt(arg);
        if (collection.size() != 0) {
            for (Person person : collection) {
                if ((person.getId() == id) && (person.getOwner().equals(connection.getId()))) {
                    if (databaseManager.removeElement(person)) {
                        collection.remove(person);
                        return "Element was removed successfully.";
                    } else return "Element was not removed therefore you are not owner of this element.";
                }
            }
            return "Element with this ID is not exist.";
        } else return "Collection is empty.";
    }
}