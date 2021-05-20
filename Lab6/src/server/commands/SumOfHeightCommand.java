package server.commands;

import server.serverCode.CollectionManager;

/**
 * Class for realizing command "sum_of_height"
 * @author Ivan Nesterov
 * @version 1.1
 */
public class SumOfHeightCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public SumOfHeightCommand(CollectionManager manager) {
        super(manager);
        setDescription("Return sum of height of all elements in collection.");
    }

    /**
     * Method for executing this command
     * @return executing status of this command
     */
    public synchronized String execute() {
        StringBuilder result = new StringBuilder();
        result.append("Sum of element`s heights is " + getManager().sum_of_height());
        getManager().save();
        return result.toString();
    }
}