package serverCode.commands;

import serverCode.ServerConnection;
import serverCode.managers.CollectionManager;

public class ClearCommand extends AbstractCommand {

    private ServerConnection serverConnection;

    public ClearCommand(ServerConnection connection) {
        this.serverConnection = connection;
        setDescription("Removes all elements of the collection.");
    }

    @Override
    public synchronized String execute() {
        CollectionManager manager = CollectionManager.getInstance();
        manager.getPersons().removeIf(p -> (p != null && p.getId() == serverConnection.getId()));
        manager.save();
        return "Из коллекции удалены все ваши элементы.";
    }
}