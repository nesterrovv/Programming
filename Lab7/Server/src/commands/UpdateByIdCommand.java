package commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Person;
import serverCode.CollectionManager;
import serverCode.DatabaseManager;
import java.io.IOException;
import java.util.Set;

public class UpdateByIdCommand extends AbstractCommand {

    private final DatabaseManager databaseManager;
    private final String owner;

    public UpdateByIdCommand(DatabaseManager databaseManager, String owner) {
        setDescription("Updates the element of collection, if it`s id equal id of entered element");
        this.databaseManager = databaseManager;
        this.owner = owner;
    }

    @Override
    public synchronized String execute(String arg) {
        XmlMapper mapper = new XmlMapper();
        try {
            CollectionManager manager = CollectionManager.getInstance();
            manager.load(databaseManager);
            Person person = mapper.readValue(arg, Person.class);
            int id = person.getId();
            Set<Person> collection = manager.getPersons();
            for (Person p : collection) {
                if (p.getId() == id && (person.getOwner().equals(owner))) {
                    if (databaseManager.updateElement(person)) {
                        collection.remove(p);
                        collection.add(person);
                        return "Element was added successfully";
                    } else return "Element was not updated because you are not owner of this file.";
                }
            }
            return "Element was not added.";

        } catch (IOException ioException) {
            return "Invalid argument. Try again.";
        }
    }
}