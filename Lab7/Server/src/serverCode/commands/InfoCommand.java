package serverCode.commands;

import data.Person;
import serverCode.ServerConnection;
import serverCode.managers.CollectionManager;

import java.util.Set;

public class InfoCommand extends AbstractCommand {

    private ServerConnection serverConnection;
    public InfoCommand(ServerConnection connection) {
        this.serverConnection = connection;
        setDescription("Prints information about the collection.");
    }

    @Override
    public synchronized String execute() {
        Set<Person> collection = CollectionManager.getInstance().getPersons();
        int amountOfElements = 0;
        for (Person p: collection) if (p.getId() == serverConnection.getId()) amountOfElements++;
        return CollectionManager.getInstance().toString() + "\nВаш номер в системе: " +
                serverConnection.getId() + "\nКоличество ваших элементов: " + amountOfElements;
    }
}