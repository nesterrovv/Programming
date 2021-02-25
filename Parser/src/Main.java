import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        // определяем название файла, куда будем сохранять
        String fileName = "data.xml";

        //создаем объект Student с какими-то данными
        Person person = new Person("Ivan", 19, false);
        Person person2 = new Person("John", 20, true);
        person.convertObjectToXml(person, "dataa.xml");
        person2.convertObjectToXml(person2, "dataa.xml");
    }
}