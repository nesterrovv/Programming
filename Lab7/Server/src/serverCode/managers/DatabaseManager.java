package serverCode.managers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

/**
 * Class {@code DatabaseManager} gives access to database PostgreSQL.studs.
 * @author Ivan Nesterov
 * @version 1.3
 * @since 04.06.21
 */
public final class DatabaseManager {

    private String DB_URL;
    private String USER;
    private String PASSWORD;
    private static volatile DatabaseManager instance;

    {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("helios_db.properties")) {
            Properties prop = System.getProperties();
            prop.load(inputStream);
            DB_URL = prop.getProperty("url_address");
            USER = prop.getProperty("user");
            PASSWORD = prop.getProperty("password");
            System.out.println("Database was configurated from helios_db.properties.");
            Tunnel tunnel = new Tunnel("se.ifmo.ru", prop.getProperty("user"), prop.getProperty("password"),
                    2222, "pg", 4241, 4242);
            tunnel.connect();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Gives access to database. Realized Singleton pattern
     */
    public static DatabaseManager getInstance() {
        DatabaseManager instance2 = instance;
        if (instance2 == null) {
            synchronized (DatabaseManager.class) {
                instance2 = instance;
                if (instance2 == null) instance = instance2 = new DatabaseManager();
            }
        }
        return instance;
    }

    /**
     * Initialized the collection and start test connection
     */
    private DatabaseManager() {
        try (Connection testConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             ResultSet testRequest = testConnection.createStatement().executeQuery("SELECT version()")) {
            System.out.println("Connecting to database...");
            while (testRequest.next())
                System.out.println("Connected successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Returns DriverManager object
     * @return object for link.
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    /**
     * Method for comparing this object
     * @param o compared object
     * @return result of comparing
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatabaseManager)) return false;
        DatabaseManager manager = (DatabaseManager) o;
        return Objects.equals(DB_URL, manager.DB_URL) &&
                Objects.equals(USER, manager.USER) &&
                Objects.equals(PASSWORD, manager.PASSWORD);
    }

    /**
     * Method for receiving hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(DB_URL, USER, PASSWORD);
    }

    /**
     * Method for printing info about file into a string representation
     * @return
     */
    @Override
    public String toString() {
        return "DatabaseManager{" +
                "DB_URL='" + DB_URL + '\'' +
                ", USER='" + USER + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                '}';
    }
}