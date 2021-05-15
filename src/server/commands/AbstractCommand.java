package server.commands;

import server.serverCode.CollectionManager;
import java.util.Objects;

public abstract class AbstractCommand {

    private CollectionManager manager;
    private String description;

    public AbstractCommand(CollectionManager manager) {
        this.manager = manager;
    }

    public synchronized String execute() {
        return "Argument is absent.";
    }

    public synchronized String execute(String arg) {
        return execute(arg);
    }

    //public  synchronized  String execute(String arg1, String arg2) {
      //  return execute();
    //}

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public CollectionManager getManager() {
        return manager;
    }

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

