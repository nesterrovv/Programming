package commands;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Person;
import serverCode.DatabaseManager;
import serverCode.ServerConnection;
import serverCode.CollectionManager;
import serverCode.UnknownUserException;

import javax.xml.crypto.Data;
import java.io.IOException;


public class AddCommand extends AbstractCommand {

    private final ServerConnection serverConnection;
    private final DatabaseManager databaseManager;

    public AddCommand(ServerConnection connection, DatabaseManager databaseManager, boolean access) {
        this.serverConnection = connection;
        this.databaseManager = databaseManager;
        setDescription("Adds new element to the collection.");
        try {
            if (!access) throw new UnknownUserException("You need to login or register before executing commands.");
        } catch (UnknownUserException unknownUserException) {
            unknownUserException.printStackTrace();
        }
    }

    public synchronized String execute(String arg) {
        try {
            CollectionManager manager = CollectionManager.getInstance();
            Person person = new XmlMapper().readValue(arg, Person.class);
            person.setId(serverConnection.getId());
            boolean flag = databaseManager.saveToDatabase(person);
            if (flag) manager.getPersons().add(person);
            else {
                return "Element was not added.";
            }
            return "Element was added successfully.";
        } catch (IOException ioException) {
            return "Invalid argument";
        }
    }
}