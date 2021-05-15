package server.commands;

import server.data.Person;
import server.serverCode.CollectionManager;
import java.util.HashSet;

public class RemoveGreaterCommand extends AbstractCommand {

    public RemoveGreaterCommand(CollectionManager manager) {
        super(manager);
        setDescription("Removes all elements which height more than current.");
    }

    public synchronized String execute(String arg) {
        Long longHeight = new Long(Long.valueOf(arg));
        long height = longHeight;
        HashSet<Person> persons = getManager().getPersons();
        int counter = 0;
        for (Person person : persons) {
            if (person.getHeight() < height) {
                persons.remove(person);
                counter += 1;
            }
        }
        getManager().save();
        return "Operation was finished successfully. " + counter + " elements were deleted.";
    }
}