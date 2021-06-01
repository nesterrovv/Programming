package serverCode.commands;

public class SaveCommand extends AbstractCommand {

    public SaveCommand() {
        setDescription("Does nothing. Saving is an automatic process :)");
    }

    @Override
    public synchronized String execute() {
        return "Don`t worry, saving is an automatic process starting after some changes in collection :)";
    }
}