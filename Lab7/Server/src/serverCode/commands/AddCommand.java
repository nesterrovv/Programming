package serverCode.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Person;
import serverCode.ServerConnection;
import serverCode.managers.CollectionManager;
import java.io.IOException;


public class AddCommand extends AbstractCommand {

    private final ServerConnection serverConnection;

    public AddCommand(ServerConnection connection) {
        this.serverConnection = connection;
        setDescription("Adds new element to the collection.");
    }

    public synchronized String execute(String arg) {
        try {
            CollectionManager manager = CollectionManager.getInstance();
            Person person = new XmlMapper().readValue(arg, Person.class);
            person.setId(serverConnection.getId());
            manager.getPersons().add(person);
            manager.save();
            return "Element was added successfully.";
        } catch (IOException ioException) {
            return "Invalid argument";
        }
    }
}