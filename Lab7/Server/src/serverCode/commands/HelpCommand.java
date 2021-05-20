package serverCode.commands;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand {

    private HashMap<String, AbstractCommand> commands;

    public HelpCommand(HashMap<String, AbstractCommand> commands) {
        setDescription("Displays help for available commands.");
        this.commands = commands;
    }

    @Override
    public synchronized String execute() {
        return commands.keySet().toString();
    }
}