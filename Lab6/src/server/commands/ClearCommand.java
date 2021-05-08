package server.commands;

import server.serverCode.CollectionManager;

public class ClearCommand extends AbstractCommand {

    public ClearCommand(CollectionManager manager) {
        super(manager);
        setDescription("Removes all elements of the collection.");
    }

    @Override
    public synchronized String execute() {
        getManager().clear();
        getManager().save();
        return "Collection is cleaned.";
    }
}