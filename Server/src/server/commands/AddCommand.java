package server.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import server.data.Person;
import server.serverCode.CollectionManager;

import java.io.IOException;

public class AddCommand extends AbstractCommand {

    public AddCommand(CollectionManager manager) {
        super(manager);
        setDescription("Adds new element to the collection.");
    }

    @Override
    public synchronized String execute(String arg) {
        try {
            XmlMapper mapper = new XmlMapper();
            getManager().getPersons().add(mapper.readValue(arg, Person.class));
            return "Element was added successfully.";
        } catch (JsonProcessingException jsonProcessingException) {
            System.out.println("Something bad with serializing a command.");
        } catch (IOException ioException) {
            System.out.println("Something bad with deserializing object to xml string");
        }
        return "Element is not added. You should write command [add param], param is serialized string\n" +
                " You can start Serializer.jar to receive this string.";
    }
}