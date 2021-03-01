
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream//;
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
        //Initialize the employees list
        Employees employees = new Employees();
        {
            employees.setEmployees(new ArrayList<Employee>());
            //Create two employees
            Employee emp1 = new Employee();
            emp1.setId(1);
            emp1.setFirstName("Lokesh");
            emp1.setLastName("Gupta");
            emp1.setIncome(100.0);

            Employee emp2 = new Employee();
            emp2.setId(2);
            emp2.setFirstName("John");
            emp2.setLastName("Mclane");
            emp2.setIncome(200.0);

            //Add the employees in list
            employees.getEmployees().add(emp1);
            employees.getEmployees().add(emp2);
        }

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

        try {
            final QName qName = new QName("employee");

            InputStream inputStream = new FileInputStream(new File("employees.xml"));

            // create xml event reader for input stream
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);

            // initialize jaxb
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            XMLEvent e = null;

            // loop though the xml stream
            while( (e = xmlEventReader.peek()) != null ) {

                // check the event is a Document start element
                if(e.isStartElement() && ((StartElement)e).getName().equals(qName)) {

                    // unmarshall the document
                    Employee contract = unmarshaller.unmarshal(xmlEventReader, Employee.class).getValue();
                    System.out.println("Ok");
                    System.out.println(contract);
                } else {
                    xmlEventReader.next();
                }
            }
        }
        catch (JAXBException jaxbException) {
            System.out.println("XML syntax error");
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found");
        }
        catch (XMLStreamException xmlStreamException) {
            System.out.println("XML Stream error");
        }
    }
}