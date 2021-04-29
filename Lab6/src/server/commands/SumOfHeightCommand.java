package server.commands;

import server.serverCode.CollectionManager;

public class SumOfHeightCommand extends AbstractCommand {

    public SumOfHeightCommand(CollectionManager manager) {
        super(manager);
        setDescription("Return sum of height of all elements in collection.");
    }

    public synchronized String execute() {
        StringBuilder result = new StringBuilder();
        result.append("Sum of element`s heights is " + getManager().sum_of_height());
        return result.toString();
        //return "Command is completed.";
    }
}