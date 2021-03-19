import data.Person;
import org.omg.CORBA.PERSIST_STORE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "persons")
@XmlAccessorType (XmlAccessType.NONE)
public class Persons
{
    @XmlElement(name = "person")
    private List<Person> persons = null;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }
}