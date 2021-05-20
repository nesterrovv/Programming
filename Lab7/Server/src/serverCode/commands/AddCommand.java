package serverCode.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Person;
import serverCode.ServerConnection;
import serverCode.managers.CollectionManager;

import java.io.IOException;


public class AddCommand extends AbstractCommand {

    private ServerConnection connection;

    public AddCommand(ServerConnection connection) {
        this.connection = connection;
        setDescription("Adds new element to the collection.");
    }

    public synchronized String execute(String[] args) {
        try {
            CollectionManager manager = CollectionManager.getInstance();
            Person person = new XmlMapper().readValue(args[0], Person.class);
            person.setId(connection.getId());
            manager.getPersons().add(person);
            manager.save();
            return "Element was added successfully.";
        } catch (IOException ioException) {
            return "Invalid argument";
        }
    }
}