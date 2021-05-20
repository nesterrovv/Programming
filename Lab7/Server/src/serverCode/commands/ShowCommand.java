package serverCode.commands;

import data.Person;
import serverCode.ServerConnection;
import serverCode.managers.CollectionManager;

import java.util.Set;

public class ShowCommand extends AbstractCommand {

    private ServerConnection serverConnection;

    public ShowCommand(ServerConnection connection) {
        this.serverConnection = connection;
        setDescription("Prints all elements in string representation to standard output.");
    }

    @Override
    public synchronized String execute() {
        CollectionManager manager = CollectionManager.getInstance();
        Set<Person> collection = manager.getPersons();
        StringBuilder result = new StringBuilder();
        if (collection.size() != 0) {
            for (Person p: collection) {
                result.append(p.toString());
                if (p.getId() == serverConnection.getId()) result.append(" ВЛАДЕЛЕЦ");
                result.append("\n    ");
            }
            return result.toString();
        }
        else return "Коллекция пуста.";
    }
}