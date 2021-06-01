package serverCode.commands;

import serverCode.managers.PasswordManager;
import serverCode.managers.DatabaseManager;
import serverCode.ServerConnection;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashMap;

/**
 * Класс {@code LoginCommand} переопределяет метод {@code execute()} для подключения к БД и поиска соответствия почты
 * и пароля в таблице users.
 * @author Артемий Кульбако
 * @version 1.2
 * @since 22.05.19
 */
public class LoginCommand extends AbstractCommand {

    private final ServerConnection serverConnection;

    public LoginCommand(ServerConnection currentConnection) {
        this.serverConnection = currentConnection;
        setDescription("Войти под своей учётной записью. Синтаксис {login email}.");
    }

    public synchronized String execute(String[] args) {
        try {
            if (args.length < 2) throw new IllegalArgumentException();
            InternetAddress email = new InternetAddress(args[0]);
            email.validate();
            try {
                PreparedStatement request = DatabaseManager.getInstance().getConnection().
                        prepareStatement("SELECT master_id FROM users WHERE email = ? AND password = ?");
                request.setString(1, email.toString());
                try {
                    request.setString(2, PasswordManager.getHash(args[1], "SHA512"));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    System.err.println("Обращение к БД без хэширования.");
                    request.setString(2, args[1]);
                }
                try (ResultSet answer = request.executeQuery()) {
                    int userID = 0;
                    while (answer.next()) userID = answer.getInt(1);
                    if (userID != 0) {
                        setAccess(userID);
                        return "Доступ разрешён.";
                    } else return "Пользователь с такими данными не найден.";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "Произошла ошибка на стороне сервера. Попробуйте ещё раз позже.";
            }
        } catch (AddressException e) {
            return e.getMessage();
        } catch (IllegalArgumentException e) {
            return execute();
        }
    }

    private void setAccess(int ID) {
        serverConnection.setId(ID);
        HashMap<String, AbstractCommand> commands = serverConnection.getAvailableCommands();
        commands.put("help", new HelpCommand());
        commands.remove("login");
        commands.remove("register");
        System.out.println("Пользователь " + serverConnection.getSocket() + " получил доступ к коллекции.");
    }
}