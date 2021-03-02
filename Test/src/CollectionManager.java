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

public class CollectionManager {
    /*
    Коллеция представляет собой список элементов, каждый из который - объект
    между тегами. Поэтому нужно засеттить в список нужные элементы и маршалить
    не элемент, а список, либо какой-то элемент списка, но обязательно что-то о
    списке, а не сам объект.
     */
    public void marshalElement(int id, String firstName, String lastName, double income, Employees employees) {
        // Инициализируем список Employees
        //Employees employees = new Employees();
        //employees.setEmployees(new ArrayList<>());
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setIncome(income);
        employees.getEmployees().add(employee);
        //employees.getEmployees().add(employee2);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Marshal the employees list in console
            jaxbMarshaller.marshal(employees, System.out);
            //Marshal the employees list in file
            jaxbMarshaller.marshal(employees, new File("employees.xml"));
        }
        catch (JAXBException jaxbException) {
            System.out.println("XML syntax error");
        }
    }

    public String unmarshalElement(String name) {
        try {
            final QName qName = new QName("employee");
            InputStream inputStream = new FileInputStream(new File(name));
            // Create XML event reader for input stream
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);
            JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            XMLEvent event;
            System.out.println("start is ok");
            if ((event = xmlEventReader.peek()) != null) {
                if (event.isStartElement() && ((StartElement) event).getName().equals(qName)) {
                    // unmarshalling
                    Employee contract = unmarshaller.unmarshal(xmlEventReader, Employee.class).getValue();
                    System.out.println("all is ok");
                    return contract.toString();
                     //return "all is ok";
                } else {
                    System.out.println("Попалась");
                    xmlEventReader.next();
                }
            } else System.out.println("Беда с башкой");
        } catch (JAXBException jaxbException) {
            System.out.println("XML syntax error");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found");
        } catch (XMLStreamException xmlStreamException) {
            System.out.println("XML Stream exception");
        }
        return "Done";
    }


}
