import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import data.*;
import java.time.ZonedDateTime;
import java.util.*;
import java.io.*;
import javax.xml.bind.JAXBException;

public class Main {

    public static void main(String[] args) {
        try {
            Person person = new Person();
            CollectionManager collectionManager = new CollectionManager();
            collectionManager.help();
        }
        catch (IOException ex) {
            System.out.println("us");
        }
    }
}
