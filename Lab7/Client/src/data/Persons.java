package data;

import data.Person;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ivan Nesterov
 * @version 1.0
 * Class for describing a collection in a marshaling-ready view
 */
@XmlRootElement(name = "persons")
@XmlAccessorType (XmlAccessType.NONE)
public class Persons
{
    /** Field persons - list for keeping collection */
    @XmlElement(name = "person")
    private List<Person> persons = null;

    /**
     * Method for get persons list
     * @return List<Person> persons
     */
    public List<Person> getPersons() {
        return persons;
    }

    /** Method for passing a value to the persons list */
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }
}