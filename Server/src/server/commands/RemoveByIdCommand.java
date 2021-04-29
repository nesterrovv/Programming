package server.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import server.data.Person;
import server.serverCode.CollectionManager;

import java.io.IOException;
import java.util.HashSet;

public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand(CollectionManager manager) {
        super(manager);
        setDescription("Removes an element finding it by it`s ID.");
    }

    //@Override
    public String execute (String id, String arg) {
        HashSet<Person> persons = getManager().getPersons();
        for (Person person : persons) {
            int intId = person.getId();
            String strId = String.valueOf(intId);
            if (strId.equals(id)) {
                persons.remove(person);
                return "Element was removed successfully.";
            }
        }
        return "Element with this ID is not exist.";
    }
}