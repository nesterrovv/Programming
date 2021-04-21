package server.commands;

import server.data.Country;
import server.serverCode.CollectionManager;

public class CountGreaterThanNationalityCommand extends AbstractCommand {

    public CountGreaterThanNationalityCommand(CollectionManager manager) {
        super(manager);
        setDescription("Counts amounts of elements which nationality greater than current.");
    }

    public synchronized String execute(Country nationality) {
        getManager().count_greater_than_nationality(nationality);
        return "Command is completed.";
    }
}