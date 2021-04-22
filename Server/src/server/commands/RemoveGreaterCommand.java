package server.commands;

import server.data.Person;
import server.serverCode.CollectionManager;

public class RemoveGreaterCommand extends AbstractCommand {

    public RemoveGreaterCommand(CollectionManager manager) {
        super(manager);
        setDescription("Removes all elements which height more than current.");
    }

    public synchronized String execute(long height) {
        getManager().remove_greater(height);
        return "Command is completed.";
    }
}