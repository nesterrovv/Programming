package server.commands;

import server.serverCode.CollectionManager;

public class GroupCountingByNationalityCommand extends AbstractCommand {

    public GroupCountingByNationalityCommand(CollectionManager manager) {
        super(manager);
        setDescription("Groups elements by nationality and returns amount of element in every group.");
    }

    public synchronized String execute() {
        getManager().group_counting_by_nationality();
        return "Command is completed.";
    }
}