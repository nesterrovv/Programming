import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class Main {
    public static void main(String[] args) {

        Employees thisEmployees = new Employees();
        thisEmployees.setEmployees(new ArrayList<>());

        CollectionManager collectionManager = new CollectionManager();
        collectionManager.marshalElement(1, "Ivan", "Ivanov", 100.0, thisEmployees);
        collectionManager.marshalElement(2, "Petr", "Petrov", 150.0, thisEmployees);
        collectionManager.marshalElement(3, "David", "Davidson", 200.0, thisEmployees);
        collectionManager.marshalElement(4, "Ann", "Man", 250.0, thisEmployees);
        System.out.println(collectionManager.unmarshalElement("employees.xml"));
    }
}