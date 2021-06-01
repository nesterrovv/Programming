package serverCode.commands;

import data.Country;
import data.Person;
import serverCode.managers.CollectionManager;

import java.util.Set;
//import serverCode.CollectionManager;

public class CountGreaterThanNationalityCommand extends AbstractCommand {

    public CountGreaterThanNationalityCommand() {
        setDescription("Counts amounts of elements which nationality greater than current.");
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