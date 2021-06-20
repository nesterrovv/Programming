package commands;

import serverCode.CollectionManager;
import serverCode.UnknownUserException;

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
        try {
            if (!access) throw new UnknownUserException("You need to login or register before executing commands.");
        } catch (UnknownUserException unknownUserException) {
            unknownUserException.printStackTrace();
        }
    }

    /**
     * Method for realizing this command
     * @return command executing status into s string representation
     */
    @Override
    public synchronized String execute() {
        System.out.println("Finishing a program.");
        System.exit(0);
        return null;
    }
}