package commands;

import data.Person;
import serverCode.CollectionManager;
import serverCode.DatabaseManager;

import java.util.Set;

public class GroupCountingByNationalityCommand extends AbstractCommand {

    private final DatabaseManager databaseManager;

    public GroupCountingByNationalityCommand(DatabaseManager databaseManager) {
        setDescription("Groups elements by nationality and returns amount of element in every group.");
        this.databaseManager = databaseManager;
    }

    public synchronized String execute() {
        CollectionManager manager = CollectionManager.getInstance();
        manager.load(databaseManager);
        Set<Person> persons = manager.getPersons();
        int chinaCounter = 0;
        int germanyCounter = 0;
        int northKoreaCounter = 0;
        for (Person person : persons) {
            switch (person.getNationality()) {
                case CHINA:
                    chinaCounter += 1;
                case GERMANY:
                    germanyCounter += 1;
                case NORTH_KOREA:
                    northKoreaCounter += 1;
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Elements of this collection were grouped by nationality." + "\n");
        result.append("First group: China. Amount of elements: " + chinaCounter + "\n");
        result.append("Second group: Germany. Amount of elements: " + germanyCounter + "\n");
        result.append("Third group: North Korea. Amount of elements: " + northKoreaCounter + "\n");
        return result.toString();
    }
}