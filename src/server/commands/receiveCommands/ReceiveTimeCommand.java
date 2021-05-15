package server.commands.receiveCommands;

import server.commands.AbstractCommand;
import server.serverCode.CollectionManager;
import java.time.ZonedDateTime;

public class ReceiveTimeCommand extends AbstractCommand {

    public ReceiveTimeCommand(CollectionManager manager) {
        super(manager);
        setDescription("Allows to receive field creationDate for object of class Person");
    }

    @Override
    public synchronized String execute() {
        return ZonedDateTime.now().toString();
    }
}

