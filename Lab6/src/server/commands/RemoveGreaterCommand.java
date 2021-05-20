package server.commands;

import server.data.Person;
import server.serverCode.CollectionManager;
import java.util.HashSet;

/**
 * Class for realizing command "remove_greater"
 * @author Ivan Nesterov
 * @version 1.1
 */
public class RemoveGreaterCommand extends AbstractCommand {


    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public RemoveGreaterCommand(CollectionManager manager) {
        super(manager);
        setDescription("Removes all elements which height more than current.");
    }

    /**
     * Method for executing this command
     * @param arg - string representation of long-type value of field "height" which will be used for comparing elements
     * @return executing status of this command
     */
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