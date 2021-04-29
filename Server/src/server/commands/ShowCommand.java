package server.commands;

import server.data.Person;
import server.serverCode.CollectionManager;
import java.util.HashSet;

public class ShowCommand extends AbstractCommand {

    public ShowCommand(CollectionManager manager) {
        super(manager);
        setDescription("Prints all elements in string representation to standard output.");
    }

    @Override
    public synchronized String execute() {
        HashSet<Person> persons = getManager().getPersons();
        StringBuilder result = new StringBuilder();
        for (Person person : persons) {
            result.append(person.toString() + "\n");
        }
        if (persons.size() != 0) {
            return result.toString();
        } else return "Collection is empty.";
    }
}