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
                String msg = "INSERT INTO shortys VALUES ('" + p.getName() + "', " + p.getCoordinates() + ", "
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