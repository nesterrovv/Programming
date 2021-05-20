package serverCode.commands;

import serverCode.ServerConnection;

public class SaveCommand extends AbstractCommand {

    private ServerConnection serverConnection;
    public SaveCommand(ServerConnection connection) {
        this.serverConnection = connection;
        setDescription("Does nothing. Saving is an automatic process :)");
    }

    @Override
    public synchronized String execute() {
        return "Don`t worry, saving is an automatic process starting after some changes in collection :)";
    }
}