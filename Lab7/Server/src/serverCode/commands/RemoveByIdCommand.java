package serverCode.commands;

import data.Person;
import serverCode.managers.CollectionManager;

import java.util.Set;

public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand() {
        setDescription("Removes element of the collection by it's id");
    }

    @Override
    public synchronized String execute(String arg) {
        CollectionManager manager = CollectionManager.getInstance();
        Set<Person> collection = manager.getPersons();
        int id = Integer.parseInt(arg);
        if (collection.size() != 0) {
            for (Person person : collection) {
                if (person.getId() == id) {
                    collection.remove(person);
                    manager.save();
                    return "Element was removed successfully.";
                }
            }
            return "Element with this ID is not exist.";
        } else return "Collection is empty.";
    }
}