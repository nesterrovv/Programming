package commands;

import serverCode.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterCommand extends AbstractCommand {

    /*
    private final DatabaseManager databaseManager;

    public RegisterCommand(DatabaseManager databaseManager) {
        setDescription("Command allows to register into system. Syntax: {register email}." +
                "\nPassword will be generated automatically and will be sent to the indicated email.");
        this.databaseManager = databaseManager;
    }

    @Override
    public synchronized String execute(String arg) {
        String[] args = arg.trim().split(" ", 2);
        String username = args[0];
        String password = args[1];
        Connection connection = databaseManager.getConnection();
        try {
            String load = "INSERT INTO users (username, password) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(load);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("Registration error. Try again.");
        }
   */
}
