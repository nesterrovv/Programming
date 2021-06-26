package commands;

import data.Person;
import serverCode.CollectionManager;
import serverCode.DatabaseManager;

import java.util.Set;

public class ShowCommand extends AbstractCommand {

    private final String owner;
    private final DatabaseManager databaseManager;

    public ShowCommand(String owner, DatabaseManager databaseManager) {
        setDescription("Prints all elements in string representation to standard output.");
        this.owner = owner;
        this.databaseManager = databaseManager;

    }

    @Override
    public synchronized String execute() {
        CollectionManager manager = CollectionManager.getInstance();
        manager.load(databaseManager);
        Set<Person> collection = manager.getPersons();
        System.out.println("loaded" + collection.toString());
        StringBuilder result = new StringBuilder();
        if (collection.size() != 0) {
            for (Person p: collection) {
                result.append(p.toString());
                if (p.getOwner().equals(owner)) result.append(" YOU ARE OWNER");
                result.append("\n    ");
            }
            return result.toString();
        }
        else return "Коллекция пуста.";
    }
}