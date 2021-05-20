package server.commands;

import server.serverCode.CollectionManager;
import java.io.*;

/**
 * Class for realizing command "execute_script"
 * @author Ivan Nesterov
 * @version 1.1
 */
public class ExecuteScriptCommand extends AbstractCommand {

    /**
     * Constructor for this class
     *
     * @param manager - field for using opportunities of Collection Manager
     */
    public ExecuteScriptCommand(CollectionManager manager) {
        super(manager);
        setDescription("Executes script from a file.");
    }

    /**
     * Method for realizing this command
     * @param nameOfFile - string representation of path to file which contain script
     * @return method executing status into a string representation
     */
    public String execute(String nameOfFile) {
        try {
            System.out.println("WARNING. To avoid recursion, your file cannot contain execute script commands.");
            BufferedReader reader = new BufferedReader(new FileReader(new File(nameOfFile)));
            //private String userCommand;
            String[] finalUserCommand;
            String command;
            while((command = reader.readLine()) != null) {
                finalUserCommand = command.trim().toLowerCase().split(" ", 3);
                switch (finalUserCommand[0]) {
                    case "help":
                        HelpCommand help = new HelpCommand(getManager());
                        help.execute();
                        break;
                    case "info":
                        InfoCommand info = new InfoCommand(getManager());
                        info.execute();
                        break;
                    case "show":
                        ShowCommand show = new ShowCommand(getManager());
                        show.execute();
                        break;
                    case "add":
                        AddCommand add = new AddCommand(getManager());
                        add.execute(finalUserCommand[1]);
                        break;
                    case "update_by_id":
                        UpdateByIdCommand update_by_id = new UpdateByIdCommand(getManager());
                        update_by_id.execute(finalUserCommand[1]);
                        break;
                    case "remove_by_id":
                        RemoveByIdCommand remove_by_id = new RemoveByIdCommand(getManager());
                        remove_by_id.execute(finalUserCommand[1]);
                        break;
                    case "clear":
                        ClearCommand clear = new ClearCommand(getManager());
                        clear.execute();
                        break;
                    case "save":
                        SaveCommand save = new SaveCommand(getManager());
                        save.execute();
                        break;
                    case "execute_script":
                        System.out.println("This script cannot to contain this command.");
                        break;
                    case "exit":
                        ExitCommand exit = new ExitCommand(getManager());
                        exit.execute();
                        break;
                    case "add_if_min":
                        AddIfMinCommand add_if_min = new AddIfMinCommand(getManager());
                        add_if_min.execute(finalUserCommand[1]);
                        break;
                    case "remove_greater":
                        RemoveGreaterCommand remove_greater = new RemoveGreaterCommand(getManager());
                        remove_greater.execute(finalUserCommand[1]);
                        break;
                    case "remove_lower":
                        RemoveLowerCommand remove_lower = new RemoveLowerCommand(getManager());
                        remove_lower.execute(finalUserCommand[1]);
                        break;
                    case "sum_of_height":
                        SumOfHeightCommand sum_of_height = new SumOfHeightCommand(getManager());
                        sum_of_height.execute();
                        break;
                    case "group_counting_by_nationality":
                        GroupCountingByNationalityCommand group_counting_by_nationality = new GroupCountingByNationalityCommand(getManager());
                        group_counting_by_nationality.execute();
                        break;
                    case "count_greater_than_nationality":
                        CountGreaterThanNationalityCommand count_greater_than_nationality = new CountGreaterThanNationalityCommand(getManager());
                        count_greater_than_nationality.execute(finalUserCommand[1]);
                        break;
                    default:
                        reader.readLine();
                        break;
                }
            }
            reader.close();
            getManager().save();
            return "Commands are ended.";
        } catch (FileNotFoundException fileNotFoundException) {
            return "File not found. Try again.";
        } catch (IOException ioException) {
            return "File reading exception. Try again.";
        }
    }
}