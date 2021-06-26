package commands;

import serverCode.DatabaseManager;
import serverCode.CollectionManager;

public class ClearCommand extends AbstractCommand {

    private final DatabaseManager databaseManager;
    private final String owner;

    public ClearCommand(DatabaseManager databaseManager, String owner) {
        setDescription("Removes all elements of the collection.");
        this.databaseManager = databaseManager;
        this.owner = owner;
    }

    @Override
    public synchronized String execute() {
        CollectionManager manager = CollectionManager.getInstance();
        manager.load(databaseManager);
        if (databaseManager.removeCollection()) {
            manager.getPersons().removeIf(p -> (p != null && p.getOwner().equals(owner)));
            return "Collection was cleaned successfully.";
        } else return "Collection was not cleaned because element";
    }
}