package server.commands;

import server.serverCode.CollectionManager;

public class ExecuteScriptCommand extends AbstractCommand {

    public ExecuteScriptCommand(CollectionManager manager) {
        super(manager);
        setDescription("Executes script from a file.");
    }

    @Override
    public synchronized String execute(String filepath) {
        getManager().execute_script(filepath);
        return "Command is completed.";
    }
}