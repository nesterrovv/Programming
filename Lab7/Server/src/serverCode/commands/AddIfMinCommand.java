package serverCode.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Person;
import serverCode.managers.CollectionManager;

import java.io.IOException;
import java.util.Set;

public class AddIfMinCommand extends AbstractCommand {

    public AddIfMinCommand() {
        setDescription("Adds an element to collection if it`s height less then min height in this collection");
    }

    @Override
    public synchronized String execute(String arg) {
        try {
            CollectionManager manager = CollectionManager.getInstance();
            Set<Person> persons = manager.getPersons();
            long minimalHeight = Long.MAX_VALUE;
            for (Person person : persons) {
                if (person.getHeight() < minimalHeight) {
                    minimalHeight = person.getHeight();
                }
            }
            Person person = new XmlMapper().readValue(arg, Person.class);
            if (person.getHeight() < minimalHeight) {
                manager.getPersons().add(person);
                manager.save();
                return "Element was added successfully.";

            } else {
                manager.save();
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