package serverCode.commands;

import serverCode.ServerConnection;
import serverCode.managers.CollectionManager;
import serverCode.managers.DatabaseManager;
import serverCode.managers.PasswordManager;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class {@code DeleteAccountCommand} overloads method {@code execute()} for removing user's account from database
 * @author Ivan Nesterov
 * @version 1.1
 */
public class DeleteAccountCommand extends AbstractCommand {

    private final ServerConnection currentConnection;

    public DeleteAccountCommand(serverCode.ServerConnection currentConnection) {
        this.currentConnection = currentConnection;
        setDescription("Removes your account. Syntax: {remove_account email password}. " +
                "All your elements also will be removed from collection into database.");
    }

    public synchronized String execute(String[] args) {
        try {
            if (args.length < 2) throw new IllegalArgumentException();
            InternetAddress email = new InternetAddress(args[0]);
            email.validate();
            try (java.sql.Connection connection = DatabaseManager.getInstance().getConnection()) {
                connection.setAutoCommit(false);
                PreparedStatement request = DatabaseManager.getInstance().getConnection().
                        prepareStatement("DELETE FROM users WHERE email = ? AND password = ?");
                request.setString(1, args[0]);
                try {
                    request.setString(2, PasswordManager.getHash(args[1], "SHA1"));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    System.err.println("Password will be written into database without hashing.");
                    request.setString(2, args[1]);
                }
                if (request.executeUpdate() == 1) {
                    try {
                        sendNotification(email);
                        CollectionManager.getInstance().getPersons().removeIf(p -> p.getId()
                                == currentConnection.getId());
                        connection.commit();
                        return "Your account was deleted";
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        connection.rollback();
                        return "Unable to delete your account due to an error on the server. Please try later.";
                    }
                } else return "User with this email or password is not exist.";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Server error. Please try later.";
            }
        } catch (AddressException e) {
            return e.getMessage();
        } catch (IllegalArgumentException e) {
            return execute();
        }
    }

    private void sendNotification(InternetAddress email) throws MessagingException {
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
            message.setSubject("Removing an account.");
            message.setText("Your account was removed from " + currentConnection.getSocket().getInetAddress());
            Transport.send(message);
            System.out.println("User having email " + email + " removed an account.");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}