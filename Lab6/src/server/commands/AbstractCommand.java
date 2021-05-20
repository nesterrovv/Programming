package server.commands;

import server.serverCode.CollectionManager;
import java.util.Objects;


/**
 * AbstractCommand - superclass for all commands for using collection
 * @author Ivan Nesterov
 * @version 1.1
 */
public abstract class AbstractCommand {

    /** Field for using opportunities of class Collection manager */
    private CollectionManager manager;
    /** Field for getting short description for a command */
    private String description;

    /** Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public AbstractCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /** Method for executing a command without argument
     *
     * @return message about not-existed argument. Should be redefined.
     */
    public synchronized String execute() {
        return "Argument is absent.";
    }

    /** Method for executing a command with argument
     *
     * @return result of executing a command
     */
    public synchronized String execute(String arg) {
        return execute(arg);
    }

    /**
     * Class for setting description
     * @param description - text of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Class for getting field manager
     * @return object of class CollectionManager (field of this class)
     */
    public CollectionManager getManager() {
        return manager;
    }

    /**
     * Class for setting manager
     * @param manager - object of class Collection manager which should be a field of this class
     */
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCommand)) return false;
        AbstractCommand that = (AbstractCommand) o;
        return Objects.equals(manager, that.manager) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manager, description);
    }

    @Override
    public String toString() {
        return "AbstractCommand{" +
                "manager=" + manager +
                ", description='" + description + '\'' +
                '}';
    }
}