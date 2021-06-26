package commands;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Person;
import serverCode.DatabaseManager;
import serverCode.CollectionManager;
import java.io.IOException;

public class AddCommand extends AbstractCommand {

    private final DatabaseManager databaseManager;
    private final String owner;

    public AddCommand(DatabaseManager databaseManager, String owner) {
        this.databaseManager = databaseManager;
        this.owner = owner;
        setDescription("Adds new element to the collection.");
    }

    public synchronized String execute(String arg) {
        try {
            CollectionManager manager = CollectionManager.getInstance();
            manager.load(databaseManager);
            //System.out.println(arg);
            Person person = new XmlMapper().readValue(arg, Person.class);
            person.setOwner(owner);
            person.setId();
            boolean flag = databaseManager.saveToDatabase(person);
            if (flag) manager.getPersons().add(person);
            else {
                return "Element was not added.";
            }
            return "Element was added successfully.";
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Invalid argument";
        }
    }
}