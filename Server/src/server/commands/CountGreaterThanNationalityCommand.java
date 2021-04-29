package server.commands;

import server.data.Country;
import server.serverCode.CollectionManager;

public class CountGreaterThanNationalityCommand extends AbstractCommand {

    public CountGreaterThanNationalityCommand(CollectionManager manager) {
        super(manager);
        setDescription("Counts amounts of elements which nationality greater than current.");
    }

    public synchronized String execute(String arg) {
        Country nationality = Country.valueOf(arg);
        StringBuilder result = new StringBuilder();
        try {
            result.append(getManager().count_greater_than_nationality(nationality));
        } catch (Exception exception) {
            System.out.println("Invalid argument.");
        }
        return result.toString();
    }
}