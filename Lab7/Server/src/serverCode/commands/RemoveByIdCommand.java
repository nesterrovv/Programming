package serverCode.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Person;
import serverCode.ServerConnection;
import serverCode.managers.CollectionManager;

import java.io.IOException;
import java.util.Set;

public class RemoveByIdCommand extends AbstractCommand {

    private ServerConnection serverConnection;

    public RemoveByIdCommand(ServerConnection connection) {
        this.serverConnection = connection;
        setDescription("Removes element of the collection by it's id");
    }

    @Override
    public synchronized String execute(String[] args) {
        XmlMapper mapper = new XmlMapper();
        try {
            CollectionManager manager = CollectionManager.getInstance();
            Person person = mapper.readValue(args[0], Person.class);
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