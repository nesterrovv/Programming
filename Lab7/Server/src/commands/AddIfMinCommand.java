package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Person;
import serverCode.CollectionManager;
import serverCode.DatabaseManager;
import java.io.IOException;
import java.util.Set;

public class AddIfMinCommand extends AbstractCommand {

    private final DatabaseManager databaseManager;
    private final String owner;

    public AddIfMinCommand(DatabaseManager databaseManager, String owner) {
        setDescription("Adds an element to collection if it`s height less then min height in this collection");
        this.databaseManager = databaseManager;
        this.owner = owner;
    }

    @Override
    public synchronized String execute(String arg) {
        try {
            CollectionManager manager = CollectionManager.getInstance();
            manager.load(databaseManager);
            Set<Person> persons = manager.getPersons();
            long minimalHeight = Long.MAX_VALUE;
            for (Person person : persons) {
                if (person.getHeight() < minimalHeight) {
                    minimalHeight = person.getHeight();
                }
            }
            Person person = new XmlMapper().readValue(arg, Person.class);
            person.setOwner(owner);
            if (person.getHeight() < minimalHeight) {
                if (databaseManager.updateElement(person)) {
                    manager.getPersons().add(person);
                    return "Element was added successfully.";
                } else {
                    return "Element was not added because database is not available at the moment.";
                }
            } else {
                return "Element wan not added to collection because its height  " +
                        "greater than lower element`s height in the collection.";
            }
        } catch (JsonProcessingException jsonProcessingException) {
            System.out.println("Something bad with serializing a command.");
        } catch (IOException ioException) {
            System.out.println("Something bad with deserializing object to xml string");
        }
        return "Element is not added";
    }
}