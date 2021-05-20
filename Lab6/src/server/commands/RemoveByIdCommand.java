package server.commands;

import server.data.Person;
import server.serverCode.CollectionManager;
import java.util.HashSet;

public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand(CollectionManager manager) {
        super(manager);
        setDescription("Removes an element finding it by it`s ID.");
    }

    //@Override
    public String execute (String id) {
        HashSet<Person> persons = getManager().getPersons();
        for (Person person : persons) {
            int intId = person.getId();
            String strId = String.valueOf(intId);
            if (strId.equals(id)) {
                persons.remove(person);
                getManager().save();
                return "Element was removed successfully.";
            }
        }
        getManager().save();
        return "Element with this ID is not exist.";
    }
}