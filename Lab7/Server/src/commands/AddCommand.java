package commands;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Person;
import serverCode.DatabaseManager;
import serverCode.ServerConnection;
import serverCode.CollectionManager;
import serverCode.UnknownUserException;
import java.io.IOException;


public class AddCommand extends AbstractCommand {

    private final ServerConnection serverConnection;
    private final DatabaseManager databaseManager;

    public AddCommand(ServerConnection connection, DatabaseManager databaseManager, boolean access) {
        this.serverConnection = connection;
        this.databaseManager = databaseManager;
        setDescription("Adds new element to the collection.");
    }

    public synchronized String execute(String arg) {
        try {
            CollectionManager manager = CollectionManager.getInstance();
            System.out.println(arg);
            Person person = new XmlMapper().readValue(arg, Person.class);
            System.out.println(person.toString());
            person.setId(person.getId());
            boolean flag = databaseManager.saveToDatabase(person);
            if (flag) manager.getPersons().add(person);
            else {
                return "Element was not added.";
            }
            return "Element was added successfully.";
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Invalid argument";
        }
    }
}