import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import data.*;
import java.lang.Runtime.*;

//import static sun.nio.ch.IOUtil.load;

//import javax.xml.bind.JAXB;


public class CollectionManager2 {

    private HashSet<data.Person> persons;
    private File xmlCollection;
    private Date initializationDate;
    private JAXB serializer;
    private boolean wasStart;
    protected static HashMap<String, String> commandsInfo;
    private JAXBContext jaxbContext;

    {
        wasStart = false;
        //serializer = new JAXB();
        persons = new HashSet<>();

        // making a manual
        commandsInfo = new HashMap<>();
        commandsInfo.put("help", " - display help for available commands");
        commandsInfo.put("info", " - print collection information to standard output");
        commandsInfo.put("show", " - print all elements in string representation to standard output");
        commandsInfo.put("add {element}", " - add new element to the collection");
        commandsInfo.put("update id {элемент}", " - update the element`s value, whose ID is equal to the given");
        commandsInfo.put("remove_by_id", " - remove an element from the collection by its ID");
        commandsInfo.put("clear", " - clear the collection");
        commandsInfo.put("save", " - save the collection to file");
        commandsInfo.put("execute_script filename", " - read and execute a script from specified file");
        commandsInfo.put("exit", "end the program (without saving to file)");
        commandsInfo.put("add_if_min {element}", "add new element to the collection, if it`s value less, " +
                "than smallest element of this collection");
        commandsInfo.put("remove_greater {element}", "remove from the collection all elements greater than the specified one");
        commandsInfo.put("remove_lower {element}", "remove from the collection all elements less than the specified one");
        commandsInfo.put("sum_of_height", "print the sum of the values of the height field for all elements of the collection");
        commandsInfo.put("group_counting_by_nationality", "group collection items by field value " +
                "nationality, display the number of items in each group");
        commandsInfo.put("count_greater_then_nationality nationality", "print the number of elements, value" +
                "whose nationality fields are greater than the given");
    }

    // Constructor for checking a path to file existence
    public CollectionManager2(String pathToFile) {
        try {
            if (pathToFile == null) throw new FileNotFoundException();
        }
        catch (FileNotFoundException exception) {
            System.out.println("When starting the program, you did not specify the path to the file" + "\n" +
                    "in which the collection is stored. Copy the file path and try again." + "\n" + "\n" +
                    "Example of correct launch:" + "\n" +
                    "For Windows: java Lab5 C:/Users/Username/Collection.xml" + "\n" +
                    "For Linux: java Lab5 /home/username/Collection.xml" + "\n" +
                    "For macOS: java Lab5 /Users/username/MyFiles/Collection.xml" + "\n" + "\n" +
                    "You need to store the collection in xml format. If you want to use a different format," +
                    "\n" + "you can write to the support service (t.me/nesterrovv)" +
                    "\n" + " so that we expand the functionality especially for you.");
            System.exit(1);
        }

        File file = new File(pathToFile);
        try {
            if (file.exists()) {
                this.xmlCollection = new File(pathToFile);
            }
            else {
                throw new FileNotFoundException();
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found. Check if the path is entered correctly and try again");
            System.exit(1);
        }
        this.xmlCollection = new File(pathToFile);
        this.initializationDate = new Date();
        this.wasStart = true;
    }

    // Deserializing from xml format to java object
    public void deserializing() throws IOException {
        int initialSize = persons.size();
        try {
            if (!xmlCollection.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File not found. Check if the path is entered correctly and try again");
            if (!wasStart) {
                System.exit(1);
            } else {
                return; // so as not to crash
            }
        }

        try {
            if (!xmlCollection.canRead() || !xmlCollection.canWrite()) {
                throw new SecurityException();
            }
        } catch (SecurityException exception) {
            System.out.println("You do not have permission to read or write the contents of this file." + "\n"
                    + "You must have these permissions for the program to work correctly.");
            if (!wasStart) {
                System.exit(1);
            } else {
                return;
            }
        }

        try {
            if (xmlCollection.length() == 0) {
                throw new JAXBException("");
            }
        } catch (JAXBException xmlException) {
            System.out.println("The file is empty.");
            if (!wasStart) {
                System.exit(1);
            } else return;
        }

        try (BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream(xmlCollection)))) {
            System.out.println("Collection  " + xmlCollection.getAbsolutePath() + " loading in progress");
            String nextLine;
            StringBuilder result = new StringBuilder();
            while ((nextLine = inputStreamReader.readLine()) != null) {
                result.append(nextLine);
            }
            try {
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                //Person person = (Person) jaxbUnmarshaller.unmarshal();
            }
            catch (JAXBException jaxbException) {
                System.out.println("XML syntax error. Failed to load collection.");
                System.exit(1);


            }
        }

    }

    // Serializing java object to xml format

/*
    JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    Person person = (Person) jaxbUnmarshaller.unmarshal(new File("C:\\file.xml"));

 */

}


