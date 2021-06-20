package commands;

import commands.AbstractCommand;
import data.Person;
import serverCode.CollectionManager;
import serverCode.DatabaseManager;
import serverCode.UnknownUserException;

import javax.xml.crypto.Data;
import java.util.Set;

public class RemoveByIdCommand extends AbstractCommand {

    private DatabaseManager databaseManager;

    public RemoveByIdCommand(DatabaseManager databaseManager) {
        setDescription("Removes element of the collection by it's id");
        this.databaseManager = databaseManager;
        try {
            if (!access) throw new UnknownUserException("You need to login or register before executing commands.");
        } catch (UnknownUserException unknownUserException) {
            unknownUserException.printStackTrace();
        }
    }

    @Override
    public synchronized String execute(String arg) {
        CollectionManager manager = CollectionManager.getInstance();
        Set<Person> collection = manager.getPersons();
        int id = Integer.parseInt(arg);
        if (collection.size() != 0) {
            for (Person person : collection) {
                if (person.getId() == id) {
                    if (databaseManager.removeElement(person)) {
                        collection.remove(person);
                        return "Element was removed successfully.";
                    } else return "Element was not removed because database is not available at the moment.";
                }
            }
            return "Element with this ID is not exist.";
        } else return "Collection is empty.";
    }
}