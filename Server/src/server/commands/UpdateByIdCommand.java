package server.commands;

import server.serverCode.CollectionManager;

public class UpdateByIdCommand extends AbstractCommand {

    public UpdateByIdCommand(CollectionManager manager) {
        super(manager);
        setDescription("Updates an element finding it by it`s ID.");
    }

    @Override
    public synchronized String execute(String id) {
        getManager().update_by_id(id);
        return "Command is completed.";
    }
}