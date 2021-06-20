package commands;

import data.Person;
import serverCode.CollectionManager;
import serverCode.UnknownUserException;

import java.util.Set;

public class SumOfHeightCommand extends AbstractCommand {


    public SumOfHeightCommand() {
        setDescription("Prints sum of values of field name of each element in collection.");
        try {
            if (!access) throw new UnknownUserException("You need to login or register before executing commands.");
        } catch (UnknownUserException unknownUserException) {
            unknownUserException.printStackTrace();
        }
    }

    @Override
    public synchronized String execute() {
        CollectionManager manager = CollectionManager.getInstance();
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