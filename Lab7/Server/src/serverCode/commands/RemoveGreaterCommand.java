package serverCode.commands;

import data.Person;
import serverCode.ServerConnection;
import serverCode.managers.CollectionManager;

import java.util.Set;

public class RemoveGreaterCommand extends AbstractCommand {

    ServerConnection serverConnection;

    public RemoveGreaterCommand(ServerConnection connection) {
        this.serverConnection = connection;
        setDescription("Removes all elements which height more than current.");
    }

    public synchronized String execute(String arg) {
        CollectionManager manager = CollectionManager.getInstance();
        long height = Long.valueOf(arg);
        Set<Person> persons = CollectionManager.getInstance().getPersons();
        int counter = 0;
        for (Person person : persons) {
            if (person.getHeight() > height) {
                persons.remove(person);
                counter += 1;
            }
        }
        manager.save();
        return "Operation was finished successfully. " + counter + " elements were deleted.";
    }
}