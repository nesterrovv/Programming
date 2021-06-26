package commands;

import data.Person;
import serverCode.CollectionManager;
import serverCode.DatabaseManager;
import java.util.Set;

public class RemoveByIdCommand extends AbstractCommand {

    private final DatabaseManager databaseManager;
    private final String owner;

    public RemoveByIdCommand(DatabaseManager databaseManager, String owner) {
        setDescription("Removes element of the collection by it's id");
        this.databaseManager = databaseManager;
        this.owner = owner;

    }

    @Override
    public synchronized String execute(String arg) {
        CollectionManager manager = CollectionManager.getInstance();
        manager.load(databaseManager);
        Set<Person> collection = manager.getPersons();
        int id = Integer.parseInt(arg);
        if (collection.size() != 0) {
            for (Person person : collection) {
                if ((person.getId() == id) && (person.getOwner().equals(owner))) {
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