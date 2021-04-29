package server.commands;

import server.serverCode.CollectionManager;

public class InfoCommand extends AbstractCommand {

    public InfoCommand(CollectionManager manager) {
        super(manager);
        setDescription("Prints information about the collection.");
    }

    @Override
    public synchronized String execute() {
        StringBuilder result = new StringBuilder();
        result.append(getManager().info());
        return result.toString();
    }
}