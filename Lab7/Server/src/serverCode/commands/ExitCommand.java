package serverCode.commands;

//import server.serverCode.CollectionManager;

import serverCode.managers.CollectionManager;

/**
 * Class for realizing command "exit"
 * @author Ivan Nesterov
 * @version 1.1
 */
public class ExitCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     *
     */
    public ExitCommand() {
        setDescription("Switches off a program.");
    }

    /**
     * Method for realizing this command
     * @return command executing status into s string representation
     */
    @Override
    public synchronized String execute() {
        CollectionManager.getInstance().save();
        return "Finishing a program.";
    }
}