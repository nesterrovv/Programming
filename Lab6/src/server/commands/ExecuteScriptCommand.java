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
            StringBuilder message = new StringBuilder();
            String[] finalUserCommand;
            String command;
            while((command = reader.readLine()) != null) {
                finalUserCommand = command.trim().toLowerCase().split(" ", 2);
                //String regex = "//s+";
                switch (finalUserCommand[0]) {
                    case "help":
                        HelpCommand help = new HelpCommand(getManager());
                        message.append(help.execute()).append("\n");
                        break;
                    case "info":
                        InfoCommand info = new InfoCommand(getManager());
                        message.append(info.execute()).append("\n");
                        break;
                    case "show":
                        ShowCommand show = new ShowCommand(getManager());
                        message.append(show.execute()).append("\n");
                        break;
                    case "add":
                        AddCommand add = new AddCommand(getManager());
                        message.append(add.execute(finalUserCommand[1])).append("\n");
                        break;
                    case "update_by_id":
                        UpdateByIdCommand update_by_id = new UpdateByIdCommand(getManager());
                        message.append(update_by_id.execute(finalUserCommand[1])).append("\n");
                        break;
                    case "remove_by_id":
                        RemoveByIdCommand remove_by_id = new RemoveByIdCommand(getManager());
                        message.append(remove_by_id.execute(finalUserCommand[1])).append("\n");
                        break;
                    case "clear":
                        ClearCommand clear = new ClearCommand(getManager());
                        message.append(clear.execute()).append("\n");
                        break;
                    case "save":
                        SaveCommand save = new SaveCommand(getManager());
                        message.append(save.execute()).append("\n");
                        break;
                    case "execute_script":
                        message.append("This script cannot to contain this command.").append("\n");
                        break;
                    case "exit":
                        ExitCommand exit = new ExitCommand(getManager());
                        message.append(exit.execute()).append("\n");
                        break;
                    case "add_if_min":
                        AddIfMinCommand add_if_min = new AddIfMinCommand(getManager());
                        message.append(add_if_min.execute(finalUserCommand[1])).append("\n");
                        break;
                    case "remove_greater":
                        RemoveGreaterCommand remove_greater = new RemoveGreaterCommand(getManager());
                        message.append(remove_greater.execute(finalUserCommand[1])).append("\n");
                        break;
                    case "remove_lower":
                        RemoveLowerCommand remove_lower = new RemoveLowerCommand(getManager());
                        message.append(remove_lower.execute(finalUserCommand[1])).append("\n");
                        break;
                    case "sum_of_height":
                        SumOfHeightCommand sum_of_height = new SumOfHeightCommand(getManager());
                        message.append(sum_of_height.execute()).append("\n");
                        break;
                    case "group_counting_by_nationality":
                        GroupCountingByNationalityCommand group_counting_by_nationality = new GroupCountingByNationalityCommand(getManager());
                        message.append(group_counting_by_nationality.execute()).append("\n");
                        break;
                    case "count_greater_than_nationality":
                        CountGreaterThanNationalityCommand count_greater_than_nationality = new CountGreaterThanNationalityCommand(getManager());
                        message.append(count_greater_than_nationality.execute(finalUserCommand[1])).append("\n");
                        break;
                    default:
                        message.append("Unknown command").append("\n");
                        //reader.readLine();
                        break;
                }
            }
            reader.close();
            getManager().save();
            return message.toString();
            //return "Commands are ended.";
        } catch (FileNotFoundException fileNotFoundException) {
            return "File not found. Try again.";
        } catch (IOException ioException) {
            return "File reading exception. Try again.";
        }
    }
}