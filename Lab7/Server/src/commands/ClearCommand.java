package commands;

import serverCode.DatabaseManager;
import serverCode.ServerConnection;
import serverCode.CollectionManager;
import serverCode.UnknownUserException;

public class ClearCommand extends AbstractCommand {

    private final ServerConnection serverConnection;
    private final DatabaseManager databaseManager;

    public ClearCommand(ServerConnection connection, DatabaseManager databaseManager) {
        this.serverConnection = connection;
        setDescription("Removes all elements of the collection.");
        this.databaseManager = databaseManager;
    }

    @Override
    public synchronized String execute() {
        CollectionManager manager = CollectionManager.getInstance();
        if (databaseManager.removeCollection()) {
            manager.getPersons().removeIf(p -> (p != null && p.getOwner().equals(serverConnection.getId())));
            return "Collection was cleaned successfully.";
        } else return "Collection was not cleaned because element";
    }
}