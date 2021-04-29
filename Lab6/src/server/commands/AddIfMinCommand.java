package server.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import server.data.Person;
import server.serverCode.CollectionManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class AddIfMinCommand extends AbstractCommand {

    public AddIfMinCommand(CollectionManager manager) {
        super(manager);
        setDescription("Adds an element to collection if it`s height less then min height in this collection");
    }

    public synchronized String execute(String arg) {
        try {
            HashSet<Person> persons = getManager().getPersons();
            long minimalHeight = Long.MAX_VALUE;
            for (Person person : persons) {
                if (person.getHeight() < minimalHeight) {
                    minimalHeight = person.getHeight();
                }
            }
            XmlMapper mapper = new XmlMapper();
            Person example = mapper.readValue(arg, Person.class);
            if (example.getHeight() < minimalHeight) {
                getManager().getPersons().add(mapper.readValue(arg, Person.class));
                return "Element was added successfully.";
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