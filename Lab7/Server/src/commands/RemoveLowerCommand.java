package commands;

import data.Person;
import serverCode.DatabaseManager;
import serverCode.CollectionManager;
import java.util.Set;

public class RemoveLowerCommand extends AbstractCommand {

    private final DatabaseManager databaseManager;
    private final String owner;

    public RemoveLowerCommand(DatabaseManager databaseManager, String owner) {
        this.databaseManager = databaseManager;
        setDescription("Removes all elements which height more than current.");
        this.owner = owner;
    }

    public synchronized String execute(String arg) {
        long height = Long.valueOf(arg);
        CollectionManager manager = CollectionManager.getInstance();
        manager.load(databaseManager);
        Set<Person> persons = manager.getPersons();
        int counter = 0;
        for (Person person : persons) {
            if (person.getHeight() < height && (person.getOwner().equals(owner))) {
                if (databaseManager.removeElement(person)) {
                    persons.remove(person);
                    counter += 1;
                }
            } else return "You cannot remove this element therefore you are not owner of this element.";
        }
        return "Operation was finished successfully. " + counter + " elements were deleted.";
    }
}