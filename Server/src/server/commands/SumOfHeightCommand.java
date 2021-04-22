package server.commands;

import server.serverCode.CollectionManager;

public class SumOfHeightCommand extends AbstractCommand {

    public SumOfHeightCommand(CollectionManager manager) {
        super(manager);
        setDescription("Return sum of height of all elements in collection.");
    }

    public synchronized String execute() {
        getManager().sum_of_height();
        return "Command is completed.";
    }
}