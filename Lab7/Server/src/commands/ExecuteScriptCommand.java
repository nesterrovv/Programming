package commands;

import serverCode.DatabaseManager;
import serverCode.ServerConnection;
import serverCode.CollectionManager;
import serverCode.UnknownUserException;

import java.io.*;

public class ExecuteScriptCommand extends AbstractCommand {

    private final ServerConnection serverConnection;
    private final DatabaseManager databaseManager;

    public ExecuteScriptCommand(ServerConnection connection, DatabaseManager databaseManager) {
        this.serverConnection = connection;
        this.databaseManager = databaseManager;
        setDescription("Executes script from a file.");
    }

    public String execute(String nameOfFile) {
        try {
            System.out.println("WARNING. To avoid recursion, your file cannot contain execute script commands.");
            BufferedReader reader = new BufferedReader(new FileReader(nameOfFile));
            String[] finalUserCommand;
            String command;
            while((command = reader.readLine()) != null) {
                finalUserCommand = command.trim().toLowerCase().split(" ", 2);
                switch (finalUserCommand[0]) {
                    case "help":
                        HelpCommand help = new HelpCommand();
                        help.execute();
                        break;
                    case "info":
                        InfoCommand info = new InfoCommand(serverConnection);
                        info.execute();
                        break;
                    case "show":
                        ShowCommand show = new ShowCommand(serverConnection);
                        show.execute();
                        break;
                    case "add":
                        AddCommand add = new AddCommand(serverConnection, databaseManager, access);
                        add.execute(finalUserCommand[1]);
                        break;
                    case "update_by_id":
                        UpdateByIdCommand update_by_id = new UpdateByIdCommand(databaseManager, serverConnection);
                        update_by_id.execute(finalUserCommand[1]);
                        break;
                    case "remove_by_id":
                        RemoveByIdCommand remove_by_id = new RemoveByIdCommand(databaseManager, serverConnection);
                        remove_by_id.execute(finalUserCommand[1]);
                        break;
                    case "clear":
                        ClearCommand clear = new ClearCommand(serverConnection, databaseManager);
                        clear.execute();
                        break;
                    case "execute_script":
                        System.out.println("This script cannot to contain this command.");
                        break;
                    case "exit":
                        ExitCommand exit = new ExitCommand();
                        exit.execute();
                        break;
                    case "add_if_min":
                        AddIfMinCommand add_if_min = new AddIfMinCommand(databaseManager);
                        add_if_min.execute(finalUserCommand[1]);
                        break;
                    case "remove_greater":
                        RemoveGreaterCommand remove_greater = new RemoveGreaterCommand(serverConnection, databaseManager);
                        remove_greater.execute(finalUserCommand[1]);
                        break;
                    case "remove_lower":
                        RemoveLowerCommand remove_lower = new RemoveLowerCommand(serverConnection, databaseManager);
                        remove_lower.execute(finalUserCommand[1]);
                        break;
                    case "sum_of_height":
                        SumOfHeightCommand sum_of_height = new SumOfHeightCommand();
                        sum_of_height.execute();
                        break;
                    case "group_counting_by_nationality":
                        GroupCountingByNationalityCommand group_counting_by_nationality = new GroupCountingByNationalityCommand();
                        group_counting_by_nationality.execute();
                        break;
                    case "count_greater_than_nationality":
                        CountGreaterThanNationalityCommand count_greater_than_nationality = new CountGreaterThanNationalityCommand();
                        count_greater_than_nationality.execute(finalUserCommand[1]);
                        break;
                    default:
                        reader.readLine();
                        break;
                }
            }
            reader.close();
            CollectionManager.getInstance().save(databaseManager);
            return "Commands are ended.";
        } catch (FileNotFoundException fileNotFoundException) {
            return "File not found. Try again.";
        } catch (IOException ioException) {
            return "File reading exception. Try again.";
        }
    }
}