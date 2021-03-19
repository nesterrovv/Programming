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
    public static void main(String[] args) throws JAXBException {
        Scanner scanner = new Scanner(System.in);
        String enter = scanner.next();
        System.out.println(enter);
/*
        //Initialize the employees list
        Employees employees = new Employees();
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
        JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //Marshal the employees list in console
        jaxbMarshaller.marshal(employees, System.out);

        //Marshal the employees list in file
        jaxbMarshaller.marshal(employees, new File("C:\\Users\\Иван Нестеров\\Documents\\GitHub\\Programming\\Test\\employees.xml"));

 */
    }


}