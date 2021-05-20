package server.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import server.data.Person;
import server.serverCode.CollectionManager;
import java.io.IOException;

/**
 * Class for realizing command for adding new element to the collection
 *
 * @author Ivan Nesterov
 * @version 1.1
 */
public class AddCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public AddCommand(CollectionManager manager) {
        super(manager);
        setDescription("Adds new element to the collection.");
    }

    /**
     * Method for executing this command
     *
     * @param arg - XML-like serialized string which contains object of class Person which will be added
     * @return executing status in string representation
     */
    public synchronized String execute(String arg) {
        try {
            Person person = new XmlMapper().readValue(arg, Person.class);
            getManager().getPersons().add(person);
            getManager().save();
            return "Element was added successfully.";
        } catch (IOException ioException) {
            return "Invalid argument";
        }
    }
}