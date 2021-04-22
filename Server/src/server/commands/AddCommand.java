package server.commands;

import server.serverCode.CollectionManager;

public class AddCommand extends AbstractCommand {

    public AddCommand(CollectionManager manager) {
        super(manager);
        setDescription("Adds new element to the collection.");
    }

    @Override
    public synchronized String execute() {
        getManager().add();
        return "Element is added.";
    }
}