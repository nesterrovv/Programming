package server.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import server.data.Person;
import server.serverCode.CollectionManager;

import java.io.IOException;
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
     * @param arg - string XML-like representation of object of class Person
     * @return executing status of this command
     */
    public synchronized String execute(String arg) {
        try {
            HashSet<Person> collection = getManager().getPersons();
            Person currentPerson = new XmlMapper().readValue(arg, Person.class);
            if (collection.size() != 0) {
                int beginSize = collection.size();
                collection.removeIf(p -> (p != null && p.compareTo(currentPerson) > 0));
                getManager().save();
                return "Amount of elements which were removed: " + (beginSize - collection.size());
            } else return "Collection is empty. Comparing is impossible.";
        } catch (IOException ioException) {
            return "Comparing error.";
        }
    }
}