package server.commands;

import server.serverCode.CollectionManager;

public class SaveCommand extends AbstractCommand {

    public SaveCommand(CollectionManager manager) {
        super(manager);
        setDescription("Does nothing. Saving is an automatic process :)");
    }

    @Override
    public synchronized String execute() {
        getManager().save();
        return "Don`t worry, saving is an automatic process starting after some changes in collection :)";
    }
}