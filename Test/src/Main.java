import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import data.*;

public class Main {

    public static void main(String[] args) {
        try {

            // Creating a XML-string representation of java object
            XmlMapper mapper = new XmlMapper();
            Person person = new Person(1, "Ivan Ivanov", new Coordinates((long) 123, new Float(123.9)),
                    ZonedDateTime.now().toString(), 185, EyeColor.GREEN, HairColor.ORANGE, Country.NORTH_KOREA,
                    new Location((long) 1234, new Double(124), "Russia"));
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(person);
            System.out.println(xml);

            // Writing XML representation of java object to file
            xmlMapper.writeValue(new File("ParsedPerson.xml"), person);

        } catch (JsonProcessingException jsonProcessingException) {
            System.out.println("Something bad with creating a XML-string representation of java object");
            System.exit(1);
        } catch (IOException ioException) {
            System.out.println("Something bad with writing XML representation of java object to file");
            System.exit(1);
        }
    }
}
