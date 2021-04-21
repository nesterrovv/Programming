package server.commands;

import server.serverCode.CollectionManager;

public class ShowCommand extends AbstractCommand {

    public ShowCommand(CollectionManager manager) {
        super(manager);
        setDescription("Prints all elements in string representation to standard output.");
    }

    @Override
    public synchronized String execute() {
        getManager().show();
        return "Elements are ended.";
    }
}