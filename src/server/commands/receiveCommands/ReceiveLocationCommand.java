package server.commands.receiveCommands;

import server.commands.AbstractCommand;
import server.data.Location;
import server.serverCode.CollectionManager;
import java.util.NoSuchElementException;

public class ReceiveLocationCommand extends AbstractCommand {

    public ReceiveLocationCommand(CollectionManager manager) {
        super(manager);
        setDescription("Allows to receive field Coordinates for object of class Person");
    }

    @Override
    public synchronized String execute() {
        try {
            return String.valueOf(new Location(getManager().receiveXLocation(), getManager().receiveYLocation(),
                    getManager().receiveNameLocation()));
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Program will be finished now.");
            System.exit(1);
        }
        return null;
    }
}


