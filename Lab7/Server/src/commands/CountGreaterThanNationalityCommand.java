package commands;

import data.Country;
import data.Person;
import serverCode.CollectionManager;
import serverCode.UnknownUserException;

import java.util.Set;

public class CountGreaterThanNationalityCommand extends AbstractCommand {

    public CountGreaterThanNationalityCommand() {
        setDescription("Counts amounts of elements which nationality greater than current.");
        try {
            if (!access) throw new UnknownUserException("You need to login or register before executing commands.");
        } catch (UnknownUserException unknownUserException) {
            unknownUserException.printStackTrace();
        }
    }

    public synchronized String execute(String arg) {
        Country country = Country.valueOf(arg);
        int exampleHashcode = country.hashCode();
        Set<Person> persons = CollectionManager.getInstance().getPersons();
        int counter = 0;
        for (Person person : persons) {
            if ((person.getNationality()).hashCode() > exampleHashcode) {
                counter += 1;
            }
        }
        return "Operation was finished successfully. " + counter + " elements.";
    }
}