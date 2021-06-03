package serverCode.commands;

//import server.serverCode.CollectionManager;

import data.Person;
import serverCode.managers.CollectionManager;

import java.util.Set;

public class GroupCountingByNationalityCommand extends AbstractCommand {

    public GroupCountingByNationalityCommand() {
        setDescription("Groups elements by nationality and returns amount of element in every group.");
    }

    public synchronized String execute() {
        CollectionManager manager = CollectionManager.getInstance();
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