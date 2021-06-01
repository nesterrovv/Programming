package serverCode.managers;

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
 * Класс {@code CollectionManager} обеспечивает доступ к коллекции.
 * @author Артемий Кульбако
 * @version 2.7
 * @since 03.06.19
 */
public final class CollectionManager {

    private Set<Person> persons;
    XmlMapper xmlMapper;
    private String collectionType;
    private Date initDate;
    private boolean collInit;
    private static volatile CollectionManager instance;

    {
        persons = Collections.synchronizedSet(new HashSet<>());
        xmlMapper = new XmlMapper();
        collectionType = "HashSet";
        collInit = false; //метка, сигнализирущая о статусе коллекции
    }

    /**
     * Предоставляет доступ к коллекции и связанному с ней файлу. Коллекция должна быть одна на приложение, поэтому
     * реализован singleton шаблон.
     */
    public static CollectionManager getInstance() {
        /*
         * Дублирующая переменная нужна для оптимизации. Поле instance объявлено как volatile, что заставляет программу
         * обновлять её значение из памяти каждый раз при доступе к переменной, тогда как значение обычной переменной
         * может быть записано в регистр процессора для более быстрого чтения. Использую дополнительную локальную
         * переменную, ускоряется работа приложения, так как значение поля обноаляется только тогда, когда действительно нужно.
         */
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
     * Загружает коллекцию из базы данных.
     */
    private CollectionManager() {
        System.out.println("Инициализация коллекции " + DatabaseManager.getInstance().getDB_URL());
        System.out.println(load());
        collInit = true;
        initDate = new Date();
    }



    /**
     * Записывает элементы коллекции в базу данных. Так как необходим нескольким командам, реализован в этом классе.
     * @return результат операции
     */
    public String save() {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
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
            return "Изменения успешно сохранены.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Не удалось сохранить изменения из-за ошибки на сервере. Попробуйте ещё раз позже.";
        } catch (IOException ioException) {
            return "Something bad with saving. Try again.";
        }
    }

    /**
     * Заполняет коллекцию данными из БД. Если коллекция загружается впервые, и возникает исключение, то метод выводит
     * стек трассировки и завершает работу программы, при возникновении исключений во время последующих загрузок только
     * выводится стек трассировки.
     * @return результат загружки коллекции.
     */
    public String load() {
        try (ResultSet answer = DatabaseManager.getInstance().getConnection().createStatement().
                executeQuery("SELECT * FROM persons")) {
            persons.clear();
            while (answer.next()) {
                int id = answer.getInt("id");
                String name = answer.getString("name");
                Coordinates coordinates = new Coordinates(answer.getLong("x"), answer.getFloat("y"));
                ZonedDateTime creationDate = xmlMapper.readValue(answer.getString("birthDate"), ZonedDateTime.class);
                long height = answer.getInt("height");
                EyeColor eyeColor = EyeColor.valueOf(answer.getString("eye color"));
                HairColor hairColor = HairColor.valueOf(answer.getString("hair color"));
                Country nationality = Country.valueOf(answer.getString("nationality"));
                Location location = new Location(answer.getLong("x location"),
                        answer.getDouble("y location"), answer.getString("name of location"));
                persons.add(new Person(id, name, coordinates, creationDate.toString(), height, eyeColor,
                        hairColor, nationality, location));
            }
            return "Коллекция загружена.";
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            if (!collInit) System.exit(1);
            return "Возникла непредвиденная ошибка. Коллекция не может быть загружена сейчас. Попробуйте позже.";
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
        return "Тип коллекции: " + persons.getClass() +
                "\nТип элементов: " + Person.class +
                "\nДата инициализации: " + initDate +
                "\nКоличество элементов: " + persons.size();
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