package server.commands;

import server.serverCode.CollectionManager;

/**
 * Class for realizing command "exit"
 * @author Ivan Nesterov
 * @version 1.1
 */
public class ExitCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public ExitCommand(CollectionManager manager) {
        super(manager);
        setDescription("Switches off a program.");
    }

    /**
     * Method for realizing this command
     * @return command executing status into s string representation
     */
    @Override
    public synchronized String execute() {
        getManager().save();
        return "Finishing a program.";
    }
}