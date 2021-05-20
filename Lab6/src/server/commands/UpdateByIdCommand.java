package server.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import server.data.Person;
import server.serverCode.CollectionManager;
import java.io.IOException;
import java.util.HashSet;

/**
 * CLass for realizing command "update_by_id"
 * @author Ivan Nesterov
 * @version 1.1
 */
public class UpdateByIdCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public UpdateByIdCommand(CollectionManager manager) {
        super(manager);
        setDescription("Updates an element finding it by it`s ID.");
    }

    /**
     * Method for executing this command
     * @param arg - XML-like serialized string which contains object of class Person which will be added
     * @return executing status in string representation
     */
    public String execute (String arg) {
        try {
            Person newPerson = new XmlMapper().readValue(arg, Person.class);
            HashSet<Person> persons = getManager().getPersons();
            String id = String.valueOf(newPerson.getId());
            for (Person person : persons) {
                int intId = person.getId();
                String strId = String.valueOf(intId);
                if (strId.equals(id)) {
                    persons.remove(person);
                    getManager().getPersons().add(newPerson);
                    getManager().save();
                    return "Element was added successfully.";
                }
            }
            return "Element was not added, because it`s id is not defined in the collection";
        } catch (IOException ioException) {
            getManager().save();
            return "File updating error.";
        }
    }
}