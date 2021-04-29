package server.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import server.data.Person;
import server.serverCode.CollectionManager;

import java.io.IOException;
import java.util.HashSet;

public class UpdateByIdCommand extends AbstractCommand {

    public UpdateByIdCommand(CollectionManager manager) {
        super(manager);
        setDescription("Updates an element finding it by it`s ID.");
    }

    //@Override
    public String execute (String id, String arg) {
        HashSet<Person> persons = getManager().getPersons();
        for (Person person : persons) {
            int intId = person.getId();
            String strId = String.valueOf(intId);
            if (strId.equals(id)) {
                persons.remove(person);
                XmlMapper mapper = new XmlMapper();
                try {
                    getManager().getPersons().add(mapper.readValue(arg, Person.class));
                    return "Element was added successfully.";
                } catch (IOException ioException) {
                    return "Something bad with updating element.";
                }
            }
        }
        return null;
    }
}