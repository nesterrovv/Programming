package server.commands;

import server.data.Person;
import server.serverCode.CollectionManager;

public class RemoveLowerCommand extends AbstractCommand {

    public RemoveLowerCommand(CollectionManager manager) {
        super(manager);
        setDescription("Removes all elements which height less than current.");
    }

    public synchronized String execute(long height) {
        getManager().remove_lower(height);
        return "Command is completed.";
    }
}