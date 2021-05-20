package server.commands;

import server.serverCode.CollectionManager;

/**
 * Class for realizing command "clear"
 *
 * @author Ivan Nesterov
 * @version 1.1
 */
public class ClearCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public ClearCommand(CollectionManager manager) {
        super(manager);
        setDescription("Removes all elements of the collection.");
    }

    /**
     * Method for executing this command
     *
     * @return executing status into a string representation
     */
    @Override
    public synchronized String execute() {
        getManager().clear();
        getManager().save();
        return "Collection is cleaned.";
    }
}