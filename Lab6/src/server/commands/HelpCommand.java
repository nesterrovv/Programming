package server.commands;

import server.serverCode.CollectionManager;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(CollectionManager manager) {
        super(manager);
        setDescription("Displays help for available commands.");
    }

    @Override
    public synchronized String execute() {
        getManager().help();
        return "Manual is ended.";
    }
}