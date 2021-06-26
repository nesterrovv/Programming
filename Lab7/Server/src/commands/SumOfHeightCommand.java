package commands;

import data.Person;
import serverCode.CollectionManager;
import serverCode.DatabaseManager;
import java.util.Set;

public class SumOfHeightCommand extends AbstractCommand {

    private final DatabaseManager databaseManager;

    public SumOfHeightCommand(DatabaseManager databaseManager) {
        setDescription("Prints sum of values of field name of each element in collection.");
        this.databaseManager = databaseManager;
    }

    @Override
    public synchronized String execute() {
        CollectionManager manager = CollectionManager.getInstance();
        manager.load(databaseManager);
        Set<Person> collection = manager.getPersons();
        if (collection.size() != 0) {
            int sum = 0;
            for (Person p: collection) {
                sum += p.getHeight();
            }
            return String.valueOf(sum);
        }
        else return "Collection is empty.";
    }
}