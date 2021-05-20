package server.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import server.data.Person;
import server.serverCode.CollectionManager;

import java.io.IOException;
import java.util.HashSet;

/**
 * Class for realizing command "remove_lower"
 * @author Ivan Nesterov
 * @version 1.1
 */
public class RemoveLowerCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public RemoveLowerCommand(CollectionManager manager) {
        super(manager);
        setDescription("Removes all elements which height less than current.");
    }

    /**
     * Method for executing this command
     * @param arg is string XML-like representation of object of class Person
     * @return executing status of this command
     */
    public synchronized String execute(String arg) {
        try {
            HashSet<Person> collection = getManager().getPersons();
            Person currentPerson = new XmlMapper().readValue(arg, Person.class);
            if (collection.size() != 0) {
                int beginSize = collection.size();
                collection.removeIf(p -> (p != null && p.compareTo(currentPerson) < 0));
                getManager().save();
                return "Amount of elements which were removed: " + (beginSize - collection.size());
            } else return "Collection is empty. Comparing is impossible.";
        } catch (IOException ioException) {
            return "Comparing error.";
        }
    }
}
