package commands;

import data.Country;
import data.Person;
import serverCode.CollectionManager;
import serverCode.DatabaseManager;

import javax.xml.crypto.Data;
import java.util.Set;

public class CountGreaterThanNationalityCommand extends AbstractCommand {

    private final DatabaseManager databaseManager;

    public CountGreaterThanNationalityCommand(DatabaseManager databaseManager) {
        setDescription("Counts amounts of elements which nationality greater than current.");
        this.databaseManager = databaseManager;
    }

    public synchronized String execute(String arg) {
        Country country = Country.valueOf(arg);
        int exampleHashcode = country.hashCode();
        CollectionManager manager = CollectionManager.getInstance();
        manager.load(databaseManager);
        Set<Person> persons = manager.getPersons();
        int counter = 0;
        for (Person person : persons) {
            if ((person.getNationality()).hashCode() > exampleHashcode) {
                counter += 1;
            }
        }
        return "Operation was finished successfully. " + counter + " elements.";
    }
}