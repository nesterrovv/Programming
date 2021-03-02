import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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

@XmlRootElement(name = "employees")
@XmlAccessorType (XmlAccessType.FIELD)
public class Employees
{
    @XmlElement(name = "employee")
    private List<Employee> employees = null;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employees=" + employees +
                '}';
    }

    public void marshalElement(int id, String firstName, String lastName, double income) {
        try {

            // making new element
            Employee newEmployee = new Employee();
            newEmployee.setId(4);
            newEmployee.setFirstName("Ivan");
            newEmployee.setLastName("Nesterov");
            newEmployee.setIncome(1000.0);
            employees.add(newEmployee);

            // marshaling an element
            System.out.println("Ok");
            JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
            System.out.println("Ok");
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            System.out.println("Ok");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println("Ok");
            employees.add(newEmployee);
            System.out.println("Ok");
            System.out.println(employees);
            //Marshal the employees list in console
            jaxbMarshaller.marshal(employees, System.out);
            System.out.println("kek");
            //Marshal the employees list in file
            jaxbMarshaller.marshal(employees, new File("employees.xml"));
            System.out.println("Ok");
        } catch (JAXBException jaxbException) {
            System.out.println("Хуевый маршалинг");
        }
    }
}