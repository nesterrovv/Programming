package server.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import server.data.Person;
import server.serverCode.CollectionManager;

import java.io.IOException;
import java.util.HashSet;

/**
 * Class for realizing command "add_fi_min"
 *
 * @author Ivan Nesterov
 * @version 1.1
 */
public class AddIfMinCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public AddIfMinCommand(CollectionManager manager) {
        super(manager);
        setDescription("Adds an element to collection if it`s height less then min height in this collection");
    }

    /**
     * Method for executing a command
     *
     * @param arg - XML-like serialized string which contains object of class Person which will be added
     * @return executing status into string representation
     */
    public synchronized String execute(String arg) {
        try {
            HashSet<Person> persons = getManager().getPersons();
            long minimalHeight = Long.MAX_VALUE;
            for (Person person : persons) {
                if (person.getHeight() < minimalHeight) {
                    minimalHeight = person.getHeight();
                }
            }
            Person person = new XmlMapper().readValue(arg, Person.class);
            if (person.getHeight() < minimalHeight) {
                getManager().getPersons().add(person);
                getManager().save();
                return "Element was added successfully.";

            } else {
                getManager().save();
                return "Element wan not added to collection because its height  " +
                        "greater than lower element`s height in the collection.";
            }
        } catch (JsonProcessingException jsonProcessingException) {
            System.out.println("Something bad with serializing a command.");
        } catch (IOException ioException) {
            System.out.println("Something bad with deserializing object to xml string");
        }
        return "Element is not added";
    }
}