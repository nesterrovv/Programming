package server.commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import server.data.Person;
import server.serverCode.CollectionManager;
import java.io.IOException;


public class AddCommand extends AbstractCommand {

    public AddCommand(CollectionManager manager) {
        super(manager);
        setDescription("Adds new element to the collection.");
    }

    public synchronized String execute(String arg) {
        try {
            Person person = new XmlMapper().readValue(arg, Person.class);
            getManager().getPersons().add(person);
            getManager().save();
            return "Element was added successfully.";
        } catch (IOException ioException) {
            return "Invalid argument";
        }
    }
}