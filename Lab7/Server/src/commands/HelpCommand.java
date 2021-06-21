package commands;

import serverCode.UnknownUserException;

import java.util.HashMap;
import java.util.Map;

public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        setDescription("Displays help for available commands.");
    }

    @Override
    public synchronized String execute() {
        StringBuilder result = new StringBuilder();
        HashMap<String, String> commands = new HashMap<>();
        commands.put("help"," - prints manual about available commands.");
        commands.put("info"," - prints information about your collection.");
        commands.put("show"," - prints all elements of your collection into a string representation.");
        commands.put("add"," - adds new element to the database.");
        commands.put("update_by_id {id}"," - updates the element using it ID. You need to write this ID.");
        commands.put("remove_by_id {id}"," - removes the element using it ID. You need to write this ID.");
        commands.put("clear"," - removes all elements of collection.");
        commands.put("execute_script {/path/to/file}"," - executes script from file. You need to write absolute path to"+
                "this script. \nAttention! Invalid commands are not executes. Therefore script should contain 1 command"+
                "in a line \nand commands must be written in the form indicated in this manual.");
        commands.put("exit"," - switches off the program.");
        commands.put("add_if_min"," - adds element to collection if it less than the smallest element of collection");
        commands.put("remove_greater"," - removes from collection all elements witch greater than current.");
        commands.put("remove_lower"," - removes from collection all elements witch lower than current.");
        commands.put("sum_of_height"," - prints sum of a field height of each element in collection");
        commands.put("group_counting_by_nationality {nationality}"," - groups all elements by its field nationality"+
                "\n and prints amount of elements in each group. Attention! You need to write this nationality."+
                "\n Variants: GERMANY, CHINA, NORTH_KOREA");
        commands.put("count_greater_than_nationality {nationality}"," - prints amount of elements which field "+
                "nationality is greater than current. Attention! You need to write this nationality."+
                "\n Variants: GERMANY, CHINA, NORTH_KOREA");
        commands.put("register {login} {password}", " - create new account. You need to come up with your" +
                "login and password.");
        commands.put("login {login} {password}", " - log in using your account. You need to write login and password");
        commands.put("delete_account {login} {password}", " - delete your account. You need to write your login and password.");
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            result.append(entry.getKey() + entry.getValue() + "\n");
        }
        return result.toString();
    }
}