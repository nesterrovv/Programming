package serverCode;

//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.*;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Class {@code CollectionManager} gives an access to collection.
 * @author Ivan Nesterov
 * @version 1.3
 * @since 03.06.2021
 */
public final class CollectionManager {

    private Set<Person> persons;
    XmlMapper xmlMapper;
    private String collectionType;
    private Date initDate;
    private boolean collInit;
    private static volatile CollectionManager instance;
    DatabaseManager databaseManager;

    {
        persons = Collections.synchronizedSet(new HashSet<>());
        xmlMapper = new XmlMapper();
        collectionType = "HashSet";
        collInit = false;
    }

    public CollectionManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * Gives link to collection, uses Singleton pattern therefore database must be once
     */
    public static CollectionManager getInstance() {

        CollectionManager instance2 = instance;
        if (instance2 == null) {
            synchronized (CollectionManager.class) {
                instance2 = instance;
                if (instance2 == null) instance = instance2 = new CollectionManager();
            }
        } return instance;
    }

    public HashMap<String, String> getCommands() {
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
        commands.put("count_greater_than_nationality {nationality}"," - prints amount of elements whcih field "+
                "nationality is greater than current. Attention! You need to write this nationality."+
                "\n Variants: GERMANY, CHINA, NORTH_KOREA");
        return commands;
    }

    /**
     *  Gives access to this class
     */
    private CollectionManager() {
        System.out.println("Initialization of the collection...");
        //System.out.println(load());
        collInit = true;
        initDate = new Date();
    }



    /**
     * Writes elements of collection to database.
     * @return result of saving
     */
    public String save(DatabaseManager databaseManager) {
        try (Connection conn = databaseManager.getConnection();
             Statement request = conn.createStatement()) {
            conn.setAutoCommit(false);
            request.addBatch("DELETE FROM persons");
            for (Person p: persons) {
                String msg = "INSERT INTO persons VALUES ('" + p.getName() + "', " + p.getCoordinates() + ", "
                        + p.getHeight() + ", " + p.getEyeColor() + ", " + p.getHairColor() + ", " + p.getNationality()
                        + ", " + p.getLocation() + ", '" + xmlMapper.writeValueAsString(p.returnCreationDate()) + "', "
                        + p.getId() + ")";
                request.addBatch(msg);
            }
            request.executeBatch();
            conn.commit();
            return "Changes are saved successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Could not load the changes. Please try later.";
        } catch (IOException ioException) {
            return "Something bad with saving. Try again.";
        }
    }

    /**
     * Loads collection from database
     * @return result of loading
     */
    public String load(DatabaseManager databaseManager) {
        try (ResultSet answer = databaseManager.getConnection().createStatement().
                executeQuery("SELECT * FROM persons")) {
            persons.clear();
            while (answer.next()) {
                int id = answer.getInt("id");
                String name = answer.getString("name");
                Coordinates coordinates = new Coordinates(answer.getLong("x"), answer.getFloat("y"));
                ZonedDateTime creationDate = xmlMapper.readValue(answer.getString("creationDate"), ZonedDateTime.class);
                long height = answer.getInt("height");
                EyeColor eyeColor = EyeColor.valueOf(answer.getString("eyeColor"));
                HairColor hairColor = HairColor.valueOf(answer.getString("hairColor"));
                Country nationality = Country.valueOf(answer.getString("nationality"));
                Location location = new Location(answer.getLong("xLocation"),
                        answer.getDouble("yLocation"), answer.getString("nameLocation"));
                persons.add(new Person(id, name, coordinates, creationDate.toString(), height, eyeColor,
                        hairColor, nationality, location, "test"));
            }
            return "Collection was loaded..";
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            if (!collInit) System.exit(1);
            return "Collection can not be loaded now. Please try later.";
        }
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public XmlMapper getXmlMapper() {
        return xmlMapper;
    }

    public String getCollectionType() {
        return collectionType;
    }

    @Override
    public String toString() {
        return "Type of collection: " + persons.getClass() +
                "\nType of elements: " + Person.class +
                "\nDate of initialization: " + initDate +
                "\nAmount of elements: " + persons.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollectionManager)) return false;
        CollectionManager that = (CollectionManager) o;
        return Objects.equals(persons, that.persons) &&
                Objects.equals(xmlMapper, that.xmlMapper) &&
                Objects.equals(collectionType, that.collectionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, xmlMapper, collectionType);
    }
}