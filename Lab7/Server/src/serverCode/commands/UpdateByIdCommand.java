package serverCode.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Person;
import serverCode.managers.CollectionManager;

import java.io.IOException;
import java.util.Set;

public class UpdateByIdCommand extends AbstractCommand {

    public UpdateByIdCommand() {
        setDescription("Updates the element of collection, if it`s id equal id of entered element");
    }

    @Override
    public synchronized String execute(String arg) {
        XmlMapper mapper = new XmlMapper();
        try {
            CollectionManager manager = CollectionManager.getInstance();
            Person person = mapper.readValue(arg, Person.class);
            int id = person.getId();
            Set<Person> collection = manager.getPersons();
            for (Person p : collection) {
                if (p.getId() == id) {
                    collection.remove(p);
                    collection.add(person);
                    manager.save();
                    return "Element was added successfully";
                }
            }
            return "Element was not added.";

        } catch (IOException ioException) {
            return "Invalid argument. Try again.";
        }
    }
}