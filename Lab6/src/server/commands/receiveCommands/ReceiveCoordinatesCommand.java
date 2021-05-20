package server.commands.receiveCommands;

import server.commands.AbstractCommand;
import server.data.Coordinates;
import server.serverCode.CollectionManager;
import java.util.NoSuchElementException;

public class ReceiveCoordinatesCommand extends AbstractCommand {

    public ReceiveCoordinatesCommand(CollectionManager manager) {
        super(manager);
        setDescription("Allows to receive field Coordinates for object of class Person");
    }

    @Override
    public synchronized String execute() {
        try {
            return String.valueOf(new Coordinates(getManager().receiveX(), getManager().receiveY()));
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Program will be finished now.");
            System.exit(1);
        }
        return null;
    }
}


