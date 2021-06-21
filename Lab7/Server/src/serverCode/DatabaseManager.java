package serverCode;

import data.*;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.concurrent.TimeUnit;

public class DatabaseManager {

    private Connection connection; // abstract class for connection

    public DatabaseManager(String URL, String name, String password) {
        while (true) {
            try {
                this.connection = DriverManager.getConnection(URL, name, password);
                System.out.println("Connection was established  successfully.");
                break;
            } catch (PSQLException psqlException) {
                System.out.println("Database is not available at the moment therefore SSH tunnel inactive. Reconnecting...");
            }
            catch (SQLException sqlException) {
                System.out.println("Database is not available at the moment. Reconnecting...");
            }
        }

    }

    public boolean saveToDatabase(Person person) {
        try {
            String insert = "INSERT INTO persons (id, name, x, y, creation_date, height, eye_color, hair_color, " +
                    "nationality, x_location, y_location, name_location, username) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setLong(3, person.getCoordinates().getX());
            preparedStatement.setFloat(4, person.getCoordinates().getY());
            preparedStatement.setString(5, person.returnCreationDate());
            preparedStatement.setLong(6, person.getHeight());
            preparedStatement.setString(7, person.getEyeColor().toString());
            preparedStatement.setString(8, person.getHairColor().toString());
            preparedStatement.setString(9, person.getNationality().toString());
            preparedStatement.setLong(10, person.getLocation().getX());
            preparedStatement.setDouble(11, person.getLocation().getY());
            preparedStatement.setString(12, person.getLocation().getName());
            preparedStatement.setString(13, person.getUser());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            System.out.println("Data loading error. Try later.");
            return false;
        }
    }

    public boolean updateElement(Person person) {
        try {
            String update = "UPDATE persons SET name = ?, x = ?, y = ?, creation_date = ?, height = ?, eye_color = ?, hair_color = ?, nationality = ?, x_location = ?, y_location = ?, name_location = ?, username = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setLong(2, person.getCoordinates().getX());
            preparedStatement.setFloat(3, person.getCoordinates().getY());
            preparedStatement.setString(4, person.returnCreationDate());
            preparedStatement.setLong(5, person.getHeight());
            preparedStatement.setString(6, person.getEyeColor().toString());
            preparedStatement.setString(7, person.getHairColor().toString());
            preparedStatement.setString(8, person.getNationality().toString());
            preparedStatement.setLong(9, person.getLocation().getX());
            preparedStatement.setDouble(10, person.getLocation().getY());
            preparedStatement.setString(11, person.getLocation().getName());
            preparedStatement.setString(12, person.getUser());
            preparedStatement.setInt(13, person.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            System.out.println("Data updating error. Try later");
            return false;
        }
    }

    public boolean removeElement(Person person) {
        try {
            String delete = "DELETE FROM persons WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, person.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            System.out.println("Data removing error. Try later.");
            return false;
        }
    }

    public boolean removeCollection() {
        try {
            String deleteAll = "DELETE FROM persons";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteAll);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            System.out.println("Data removing error. Try later.");
            return false;
        }
    }

    public boolean login(String username, String password) {
        try {
            String login = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(login);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int amount = preparedStatement.executeUpdate();
            if (amount == 1) {
                return true;
            } else if(amount > 1) {
                System.out.println("Database error. Username is not unique. Try later.");
                System.exit(1);
                return false;
            } else throw new SQLException();
        } catch (SQLException sqlException) {
            System.out.println("User not found. You can register this account.");
            return false;
        }
    }

    public boolean register(String username, String password) {
        try {
            String login = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(login);
            preparedStatement.setString(1, username);
            System.out.println("processing");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int amount = resultSet.getInt(1);
            System.out.println(amount);
            if (amount == 0) {
                String register = "INSERT INTO users (username, password) values (?, ?)";
                PreparedStatement preparedStatement2 = connection.prepareStatement(register);
                preparedStatement2.setString(1, username);
                preparedStatement2.setString(2, password);
                preparedStatement2.execute();
                System.out.println("alive");
                return true;
            } else {
                System.out.println("User is already exist.");
                return false;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("User is already exist.");
            //System.out.println();
            return false;
        }
    }

    public String getURL() {
        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
