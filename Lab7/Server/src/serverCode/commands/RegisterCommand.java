package serverCode.commands;

import serverCode.managers.PasswordManager;
import serverCode.managers.DatabaseManager;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Класс {@code RegisterCommand} command which overrides method
 * {@code execute()} for realizing connection to database
 * @author Ivan Nesterov
 * @version 1.1
 */
public class RegisterCommand extends AbstractCommand {

    public RegisterCommand() {
        setDescription("Command allows to register into system. Syntax: {register email}." +
                "\nPassword will be generated automatically and will be sent to the indicated email.");
    }

    @Override
    public synchronized String execute(String arg) {
        try {
            InternetAddress email = new InternetAddress(arg);
            email.validate();
            String password = PasswordManager.generateNewPassword();
            try (Connection connection = DatabaseManager.getInstance().getConnection()) {
                connection.setAutoCommit(false);
                PreparedStatement request = connection.prepareStatement("INSERT INTO users (email, password) " +
                        "SELECT ?, ? WHERE NOT EXISTS (SELECT email FROM users WHERE email = ?)");
                request.setString(1, email.toString());
                try {
                    request.setString(2, PasswordManager.getHash(password, "SHA512"));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    System.err.println("Password will be written to the database without hashing.");
                    request.setString(2, password);
                }
                request.setString(3, email.toString());
                if (request.executeUpdate() == 1) {
                    try {
                        sendPassword(email, password);
                        connection.commit();
                        return "Registration was finished successfully. Password is sent to indicated email.";
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        connection.rollback();
                        return "Registration is not finished successfully. Please try later.";
                    }
                } else return "Account with indicated email is already exist.";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Something bad with receiving request to database. Please try again.";
            }
        } catch (AddressException e) {
            return e.getMessage();
        }
    }

    private void sendPassword(InternetAddress email, String password) throws MessagingException {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mail.properties")) {
            Properties prop = System.getProperties();
            prop.load(inputStream);
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(prop.getProperty("sender_email"), prop.getProperty("sender_password"));
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(prop.getProperty("sender_email")));
            message.setRecipient(Message.RecipientType.TO, email);
            message.setSubject("Пароль для регистрации в lab7.");
            message.setText("Пароль: " + password);
            Transport.send(message);
            System.out.println("User with email " + email + " was registered into the system.");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}