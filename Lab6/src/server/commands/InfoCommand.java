package server.commands;

import server.serverCode.CollectionManager;

/**
 * Class for realizing command "info"
 * @author Ivan Nesterov
 * @version 1.1
 */
public class InfoCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public InfoCommand(CollectionManager manager) {
        super(manager);
        setDescription("Prints information about the collection.");
    }

    /**
     * Method for executing this command
     * @return executing status of this command into a string representation
     */
    @Override
    public synchronized String execute() {
        StringBuilder result = new StringBuilder();
        result.append(getManager().info());
        getManager().save();
        return result.toString();
    }
}