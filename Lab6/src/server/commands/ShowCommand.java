package server.commands;

import server.data.Person;
import server.serverCode.CollectionManager;
import java.util.HashSet;

/**
 * Class for realizing command "show"
 * @author Ivan Nesterov
 * @version 1.1
 */
public class ShowCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public ShowCommand(CollectionManager manager) {
        super(manager);
        setDescription("Prints all elements in string representation to standard output.");
    }

    /**
     * Method for executing this command
     * @return executing status of this command
     */
    @Override
    public synchronized String execute() {
        HashSet<Person> persons = getManager().getPersons();
        StringBuilder result = new StringBuilder();
        for (Person person : persons) {
            result.append(person.toString() + "\n");
        }
        if (persons.size() != 0) {
            return result.toString();
        } else return "Collection is empty.";
    }
}