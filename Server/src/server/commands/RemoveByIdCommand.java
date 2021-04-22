package server.commands;

import server.serverCode.CollectionManager;

public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand(CollectionManager manager) {
        super(manager);
        setDescription("Removes an element finding it by it`s ID.");
    }

    @Override
    public synchronized String execute(String id) {
        getManager().remove_by_id(id);
        return "Command is completed.";
    }
}