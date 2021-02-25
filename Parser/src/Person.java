import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.Objects;

@XmlRootElement(name = "Person")

public class Person {
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="age")
    private int age;
    @XmlElement(name="isAlive")
    private boolean isAlive;

    public Person(String name, int age, boolean isAlive) {
        this.name = name;
        this.age = age;
        this.isAlive = isAlive;
    }

    Person() {
        String name;
        int age;
        boolean isAlive;
    }

    public String getName() {
        return name;
    }
/*
    public void setName(String name) {
        this.name = name;
    }
*/

    public int getAge() {
        return age;
    }
/*
    public void setAge(int age) {
        this.age = age;
    }
*/
    public boolean isAlive() {
        return isAlive;
    }
/*
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
*/
    // сохраняем объект в XML файл
    public void convertObjectToXml(Person person, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(person, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // восстанавливаем объект из XML файла
    public Person fromXmlToObject(String filePath) {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Person) un.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                isAlive == person.isAlive &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, isAlive);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name = " + name +
                ", age = " + age +
                ", isAlive = " + isAlive +
                '}';
    }
}
