package server.commands;

import server.serverCode.CollectionManager;

public class ExitCommand extends AbstractCommand {

    public ExitCommand(CollectionManager manager) {
        super(manager);
        setDescription("Switches off a program.");
    }

    @Override
    public synchronized String execute() {
        getManager().exit();
        return "Command is completed.";
    }
}