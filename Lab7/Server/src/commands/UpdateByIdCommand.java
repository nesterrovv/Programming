package commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Person;
import serverCode.CollectionManager;
import serverCode.DatabaseManager;
import serverCode.UnknownUserException;

import java.io.IOException;
import java.util.Set;

public class UpdateByIdCommand extends AbstractCommand {

    private final DatabaseManager databaseManager;

    public UpdateByIdCommand(DatabaseManager databaseManager) {
        setDescription("Updates the element of collection, if it`s id equal id of entered element");
        this.databaseManager = databaseManager;
        try {
            if (!access) throw new UnknownUserException("You need to login or register before executing commands.");
        } catch (UnknownUserException unknownUserException) {
            unknownUserException.printStackTrace();
        }
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
                    if (databaseManager.updateElement(person)) {
                        collection.remove(p);
                        collection.add(person);
                        return "Element was added successfully";
                    } else return "Element was not updated because database is not available at the moment.";
                }
            }
            return "Element was not added.";

        } catch (IOException ioException) {
            return "Invalid argument. Try again.";
        }
    }
}