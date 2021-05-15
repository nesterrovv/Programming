package server.commands;

import server.serverCode.CollectionManager;
import java.util.HashMap;
import java.util.Map;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(CollectionManager manager) {
        super(manager);
        setDescription("Displays help for available commands.");
    }

    @Override
    public synchronized String execute() {
        StringBuilder result = new StringBuilder();
        HashMap<String, String> commandsInfo = getManager().getInfoCommands();
        for (Map.Entry<String, String> entry : commandsInfo.entrySet()) {
            result.append(entry.getKey() + entry.getValue() + "\n");
        }
        getManager().save();
        return result.toString();
    }
}