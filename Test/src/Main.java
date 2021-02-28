import java.io.File;
import java.util.*;
import javax.xml.bind.*;


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
/*
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

 */
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //We had written this file in marshalling example
            Employees emps = (Employees) jaxbUnmarshaller.unmarshal( new File("employees.xml") );

            for(Employee emp : emps.getEmployees())
            {
                System.out.println(emp.getId());
                System.out.println(emp.getFirstName());
            }
        }
        catch (JAXBException jaxbException) {
            System.out.println("XML syntax error");
        }
    }
}