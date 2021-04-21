package server.commands;

import server.data.Person;
import server.serverCode.CollectionManager;

public class AddIfMinCommand extends AbstractCommand {

    public AddIfMinCommand(CollectionManager manager) {
        super(manager);
        setDescription("Adds an element to collection if it`s height less then min height in this collection");
    }

    public synchronized String execute(Person person) {
        getManager().add_if_min(person);
        return "Command is completed.";
    }
}