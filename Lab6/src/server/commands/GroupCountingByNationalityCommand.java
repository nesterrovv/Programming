package server.commands;

import server.serverCode.CollectionManager;

/**
 * Class for realizing command "group_counting_by_nationality"
 *
 * @author Ivan Nesterov
 * @version 1.1
 */
public class GroupCountingByNationalityCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public GroupCountingByNationalityCommand(CollectionManager manager) {
        super(manager);
        setDescription("Groups elements by nationality and returns amount of element in every group.");
    }

    /**
     * Method for realizing this command
     *
     * @return command execution status into a string representation
     */
    public synchronized String execute() {
        StringBuilder result = new StringBuilder();
        result.append(getManager().group_counting_by_nationality());
        getManager().save();
        return result.toString();
    }
}