import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import data.*;

/**
 * @author Ivan Nesterov
 * @version 1.0
 * Class which realised user`s commands
 */
public class CollectionManager {

    /** HashSet collection for keeping a collection as java-object */
    private final HashSet<data.Person> persons;
    /** Field used for saving collection into xml file */
    private File xmlCollection;
    /** Field for saving date of initialization thw collection */
    private Date initializationDate;
    /** Field for checking the program was started */
    private boolean wasStart;
    /** HashMap collection for making a manual */
    private final HashMap<String, String> commandsInfo;
    /** The field for storing the hash in order to check that the file has not been artificially modified */
    String hash;

    {
        wasStart = false;
        persons = new HashSet<>();

        // Making a manual
        commandsInfo = new HashMap<>();
        commandsInfo.put("help", " - display help for available commands");
        commandsInfo.put("info", " - print all elements in string representation to standard output");
        commandsInfo.put("add", " - add new element to the collection");
        commandsInfo.put("update_by_id id", " - update the element`s value, whose ID is equal to the given." +
                " You should to enter ID after entering a command.");
        commandsInfo.put("remove_by_id id", " - remove an element from the collection by its ID." +
                " You should to enter ID after entering a command.");
        commandsInfo.put("clear", " - clear the collection");
        commandsInfo.put("save", " - save the collection to file");
        commandsInfo.put("execute_script filename", " - read and execute a script from specified file." +
                " You should to enter path to file after entering a command.");
        commandsInfo.put("exit", " - end the program (without saving to file)");
        commandsInfo.put("add_if_min", " - add new element to the collection, if it`s value less, " +
                "than smallest element of this collection. You should to enter characteristics of" +
                " comparing element after entering a command.");
        commandsInfo.put("remove_greater", " - remove from the collection all elements greater than the specified" +
                " one. You should to enter a height which will be comparing with element`s heights.");
        commandsInfo.put("remove_lower", " - remove from the collection all elements less than the specified one." +
                " You should to enter a height which will be comparing with element`s heights.");
        commandsInfo.put("sum_of_height", " - print the sum of the values of the height field for all elements" +
                " of the collection");
        commandsInfo.put("group_counting_by_nationality", " - group collection items by field value " +
                "nationality, display the number of items in each group");
        commandsInfo.put("count_greater_than_nationality nationality", " - print the number of elements, value" +
                "whose nationality fields are greater than the given. You should to enter a nationality which will" +
                " be comparing with element`s heights.");
    }

    // Constructor for checking a path to file existence and file readiness to work
    public CollectionManager() {
        Scanner scanner = new Scanner(System.in);
        try {
            for ( ; ; ) {
                System.out.print("Enter a full path to XML file with collection: ");
                String pathToFile = scanner.nextLine().trim().toLowerCase();
                File file = new File(pathToFile);
                if (file.exists()) {
                    if (file.canRead()) {
                        if (file.canWrite()) {

                            this.xmlCollection = new File(pathToFile);
                            this.initializationDate = new Date();
                            this.wasStart = true;

                            System.out.println("Collection manager was stated successfully.");

                            try {
                                final QName qName = new QName("person");

                                InputStream inputStream = new FileInputStream(new File("persons.xml"));

                                // create xml event reader for input stream
                                XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
                                XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);

                                // initialize jaxb
                                JAXBContext context = JAXBContext.newInstance(Person.class);
                                Unmarshaller unmarshaller = context.createUnmarshaller();

                                String checker = "";
                                XMLEvent e;
                                // Field for counting amount of downloaded elements
                                int counter = 0;
                                // Loop for unmarshalling the collection
                                while ((e = xmlEventReader.peek()) != null) {
                                    // check the event is a Document start element
                                    if (e.isStartElement() && ((StartElement) e).getName().equals(qName)) {
                                        // unmarshall the document
                                        Person unmarshalledPerson = unmarshaller.unmarshal(xmlEventReader, Person.class).getValue();
                                        persons.add(unmarshalledPerson);
                                        counter += 1;
                                    } else {
                                        xmlEventReader.next();
                                    }
                                }
                                /*
                                 Checking that the file has not been modified.
                                 Implemented by comparing the current hash of the file with the previous one */
                                try {
                                    File myFile = new File("hash.txt");
                                    FileReader fr = new FileReader(myFile);
                                    BufferedReader reader = new BufferedReader(fr);
                                    checker = reader.readLine();
                                    reader.close();
                                } catch (FileNotFoundException fileNotFoundException) {
                                    System.out.println("File not found. Try again.");
                                } catch (IOException ioException) {
                                    System.out.println("File saving critical error.");
                                }
                                if (org.apache.commons.codec.digest.DigestUtils.md5Hex(Files.newInputStream(Paths.get(pathToFile))).equals(checker)) {
                                    System.out.println("Collection was loaded successfully. " + counter +
                                            " elements has been loaded.");
                                } else {
                                    System.out.println("You changed the file artificially, without using a" +
                                            " collection manager.");
                                    System.out.println(" The file is damaged and the program cannot work.");
                                    System.out.println(" The file is damaged and the program cannot work.");
                                    System.out.println("Contact support (https://t.me/nesterrovv) to resolve" +
                                            " this issue");
                                    System.exit(1);
                                }
                            } catch (JAXBException jaxbException) {
                                System.out.println("XML syntax error.");
                            } catch (FileNotFoundException fileNotFoundException) {
                                System.out.println("File not found");
                            } catch (XMLStreamException xmlStreamException) {
                                System.out.println("XML Stream error");
                            } catch (IOException ioException) {
                                System.out.println("Runtime exception. Try again.");
                            }
                            break;
                        }
                    }
                } else {
                    if (!file.exists()) {
                        System.out.println("File is not exist. Try again.");
                    } else {
                        if (!file.canRead()) {
                            System.out.println("File cannot be readable. You should to give this permission.");
                        } else {
                            if (!file.canWrite()) {
                                System.out.println("File cannot be writeable. You should to give this permission.");
                            }
                        }
                    }
                }
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Program will be finished now.");
            System.exit(0);
        }
    }

    /**
     * Check file for readiness to work
     * @return readiness status
     * */
    public boolean checkFile() {
        if (xmlCollection.exists()) {
            if (xmlCollection.canRead()) {
                return xmlCollection.canWrite();
            }
        } else {
            if (!xmlCollection.exists()) {
                System.out.println("File is not exist. Try again.");
                return false;
            } else {
                if (!xmlCollection.canRead()) {
                    System.out.println("File cannot be readable. You should to give this permission.");
                    return false;
                } else {
                    if (!xmlCollection.canWrite()) {
                        System.out.println("File cannot be writeable. You should to give this permission.");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /** Method for printing manual for user */
    public void help() {
        for (Map.Entry<String, String> entry : commandsInfo.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    /** Method for printing information about the collection */
    public void info() {
        System.out.println("Type of collection: java.util.HashSet");
        System.out.println("Initialization date: " + initializationDate);
        System.out.println("Amount of elements in the collection: " + persons.size());
        System.out.println("Collection manager is active: " + wasStart);
    }

    /** Method for printing collection elements into the string representation */
    public void show() {
        for (Person person : persons) {
            System.out.println(person.toString() + "\n");
        }
    }

    /**
     * Method for receiving ID of element
     * @return int ID
     */
    public int receiveId() {
        int maxId = 0;
        for (Person person : persons) {
            if (person.getId() > maxId) {
                maxId = person.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Method for receiving name of element
     * @return String name
     */
    public String receiveName() {
        for ( ; ; ) {
            try {
                System.out.println("Attention! If name will be so long, you may lose some part of this information");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a name: ");
                String name = scanner.nextLine().trim();
                if (name.equals("")) {
                    System.out.println("This value cannot be empty. Try again");
                    continue;
                }
                return name;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be string. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving x-coordinate of element
     * @return long x
     */
    public long receiveX() {
        for ( ; ; ) {
            try {
                System.out.print("Enter X coordinate. Max value is 690. ");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Value cannot be empty. ");
                long x = scanner.nextLong();
                System.out.print("Value cannot be empty. ");
                String xx = Long.toString(x);
                if (x > 690) {
                    System.out.println("Max value is 690. Try again. ");
                    continue;
                }
                if (xx.equals("") ) {
                    System.out.println("This value cannot be empty. Try again. ");
                    continue;
                }
                return x;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving y-coordinate of element
     * @return Float y
     */
    public Float receiveY() {
        for ( ; ; ) {
            try {
                System.out.print("Enter Y coordinate. This value cannot be empty. ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextFloat();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a float-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /** Method for making coordinates by using methods receiveX() and receiveY() */
    public Coordinates receiveCoordinates() {
        return new Coordinates(receiveX(), receiveY());
    }

    /**
     * Method for receiving height of element
     * @return long height
     */
    public long receiveHeight() {
        for ( ; ; ) {
            try {
                System.out.print("Enter height. Value must be greater than 0. ");
                Scanner scanner = new Scanner(System.in);
                long height = scanner.nextLong();
                if (height <= 0) {
                    System.out.println("This value must be greater than 0. Try again. ");
                    continue;
                }
                return height;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving x-coordinate of location of element
     * @return long xLocation
     */
    public long receiveXLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Enter X coordinate of location. ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextLong();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving y-coordinate of element
     * @return Double yLocation
     */
    public Double receiveYLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Enter Y coordinate of location: ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextDouble();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving name of element`s location
     * @return String nameLocation
     */
    public String receiveNameLocation() {
        for ( ; ; ) {
            try {
                System.out.println("Attention! If name will be so long, you may lose some part of this information");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a name of location: ");
                String nameLocation = scanner.nextLine().trim();
                if (nameLocation.equals("")) {
                    System.out.println("This value cannot be empty. Try again. ");
                }
                return nameLocation;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a string. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving Location field by using methods receiveXLocation(),
     * receiveYLocation() and receiveNameLocation()
     * @return Location location
     */
    public Location receiveLocation() {
        return new Location(receiveXLocation(), receiveYLocation(), receiveNameLocation());
    }

    /**
     * Method for receiving eye color of element
     * @return EyeColor eyeColor
     */
    public EyeColor receiveEyeColor() {
        for ( ; ; ) {
            try {
                System.out.println("Choose variant of eye color. Enter the number corresponding to the desired option. ");
                System.out.print("Variants: 1 - GREEN, 2 - RED, 3 - BLUE. Enter 1, 2 or 3: ");
                Scanner scanner = new Scanner(System.in);
                int eyeChoose = scanner.nextInt();
                switch (eyeChoose) {
                    case 1:
                        return EyeColor.GREEN;
                    case 2:
                        return EyeColor.RED;
                    case 3:
                        return EyeColor.BLUE;
                    default:
                        break;
                }
                System.out.println("You should to enter 1, 2 or 3. Try again. ");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number (1, 2 or 3). Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving hair color of element
     * @return HairColor hairColor
     */
    public HairColor receiveHairColor() {
        for ( ; ; ) {
            try {
                System.out.println("Choose variant of hair color. Enter the number corresponding to the desired option.");
                System.out.print("Variants: 1 - BLUE, 2 - YELLOW, 3 - ORANGE. Enter 1, 2 or 3: ");
                Scanner scanner = new Scanner(System.in);
                int hairChoice = scanner.nextInt();
                switch (hairChoice) {
                    case 1:
                        return HairColor.BLUE;
                    case 2:
                        return HairColor.YELLOW;
                    case 3:
                        return HairColor.ORANGE;
                    default:
                        break;
                }
                System.out.println("You should to enter 1, 2 or 3. Try again. ");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number (1, 2 or 3). Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving nationality of element
     * @return Country nationality
     */
    public Country receiveNationality() {
        for ( ; ; ) {
            try {
                System.out.println("Choose variant of hair color. Enter the number corresponding to the desired option. ");
                System.out.print("Variants: 1 - GERMANY, 2 - CHINA, 3 - NORTH KOREA. Enter 1, 2 or 3: ");
                Scanner scanner = new Scanner(System.in);
                int nationalityChoice = scanner.nextInt();
                switch (nationalityChoice) {
                    case 1:
                        return Country.GERMANY;
                    case 2:
                        return Country.CHINA;
                    case 3:
                        return Country.NORTH_KOREA;
                    default:
                        break;
                }
                System.out.println("You should to enter 1, 2 or 3 (1, 2 or 3). Try again. ");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /** Method for adding element by using all receive-fields methods */
    public void add() {
        Person newPerson = new Person(receiveId(), receiveName(), receiveCoordinates(), returnDate(),
                receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(), receiveLocation());
        persons.add(newPerson);
        if (checkFile()) {
            System.out.println("Element was added successfully. You should to save changes into a file. ");
        } else {
            System.out.println("Resolve the indicated problem. Otherwise, the results of your work will not be saved. ");
        }
    }

    /** Method for saving (marshaling) java collection to XML-file and updating hash of file */
    public void save() {
        try {
            Persons newPersons = new Persons();
            newPersons.setPersons(new ArrayList<>(persons));
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Marshal the persons list in file
            jaxbMarshaller.marshal(newPersons, xmlCollection);
            hash = hashCollection("persons.xml");
            try {
                FileWriter fileWriter = new FileWriter("hash.txt", false);
                fileWriter.write(hash);
                fileWriter.flush();
            } catch (IOException ioException) {
                System.out.println("File saving critical error.");
            }
        }
        catch (JAXBException jaxbException) {
            System.out.println("XML syntax error. Try again. :-(");
        }
    }

    /**
     * Method for receiving hash of the file
     * @return String hash
     */
    public String hashCollection(String filename) {
        try (InputStream is = Files.newInputStream(Paths.get(filename))) {
            return org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
        } catch (IOException ioException) {
            System.out.print("File saving critical error. Try again.");
            System.exit(1);
            return null;
        }
    }

    /** Method for remove elements from collection if it`s height more than entered height */
    public void remove_greater(long height) {
        int counter = 0;
        for (Person person : persons) {
            if (person.getHeight() > height) {
                persons.remove(person);
                counter += 1;
            }
        }
        System.out.println("Operation was finished successfully. " + counter + " elements were deleted.");
    }

    /** Method for remove elements from collection if it`s height less than entered height */
    public void remove_lower(long height) {
        int counter = 0;
        for (Person person : persons) {
            if (person.getHeight() < height) {
                persons.remove(person);
                counter += 1;
            }
        }
        System.out.println("Operation was finished successfully. " + counter + " elements were deleted.");
    }

    /** Method for adding element to collection if it`s height less than entered height */
    public void add_if_min(Person example) {
        long minimalHeight = Long.MAX_VALUE;
        for (Person person : persons) {
            if (person.getHeight() < minimalHeight) {
                minimalHeight = person.getHeight();
            }
        }
        if (example.getHeight() < minimalHeight) {
            persons.add(example);
            System.out.println("Element was added successfully.");
        } else {
            System.out.println("Element wan not added to collection because its height  " +
                               "greater than lower element`s height in the collection.");
        }
    }

    /** Method for printing sum of element`s heights */
    public void sum_of_height() {
        double sum = 0;
        for (Person person : persons) {
            sum += person.getHeight();
        }
        System.out.println("Sum of height values in this collection is " + sum);
    }

    /** Method for switching off the program */
    public void exit() {
        try {
            System.out.println("Program will be finished now. ");
            TimeUnit.SECONDS.sleep(3);
            System.exit(0);
        } catch (InterruptedException interruptedException) {
            System.out.println("Program will be finished now.");
            System.exit(0);
        }
    }

    /** Method for removing all elements from collection */
    public void clear() {
        persons.clear();
        System.out.println("Collection was cleaned successfully.");
    }

    /** Method for counting amount of elements, which nationality`s hashcode greater than entered nationality */
    public void count_greater_than_nationality (Country country) {
        int exampleHashcode = country.hashCode();
        int counter = 0;
        for (Person person : persons) {
            if ((person.getNationality()).hashCode() > exampleHashcode) {
                counter += 1;
            }
        }
        System.out.println("Operation was finished successfully. " + counter + " elements.");
    }

    /** Method for removing the element by it`s ID */
    public void remove_by_id(String id) {
        for (Person person : persons) {
            int intId = person.getId();
            String strId = String.valueOf(intId);
            if (strId.equals(id)) {
                persons.remove(person);
                System.out.println("Element was deleted successfully.");
            }
        }
        System.out.println("Element with this ID is not defined.");
    }

    /** Method for updating the element by it`s ID */
    public void update_by_id(String id) {
        for (Person person : persons) {
            int intId = person.getId();
            String strId = String.valueOf(intId);
            if (strId.equals(id)) {
                persons.remove(person);
                Person updatedPerson = new Person(intId, receiveName(), receiveCoordinates(), person.returnCreationDate(),
                        receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(), receiveLocation());
                persons.add(updatedPerson);
                System.out.println("Element was updated successfully.");
            }
        }
        System.out.println("Element with this ID is not defined. Try again.");
        System.out.println("Element with this ID is not defined.");
    }

    /** Method for counting amount and grouping elements by it`s nationality field */
    public void group_counting_by_nationality() {
        int chinaCounter = 0;
        int germanyCounter = 0;
        int northKoreaCounter = 0;
        for (Person person : persons) {
            switch (person.getNationality()) {
                case CHINA:
                    chinaCounter += 1;
                case GERMANY:
                    germanyCounter += 1;
                case NORTH_KOREA:
                    northKoreaCounter += 1;
            }
        }
        System.out.println("Elements of this collection were grouped by nationality.");
        System.out.println("First group: China. Amount of elements: " + chinaCounter);
        System.out.println("Second group: Germany. Amount of elements: " + germanyCounter);
        System.out.println("Third group: North Korea. Amount of elements: " + northKoreaCounter);
    }

    /** Method for executing script from external file */
    public void execute_script(String nameOfFile) {
        try {
            System.out.println("WARNING. To avoid recursion, your file cannot contain execute script commands.");
            BufferedReader reader = new BufferedReader(new FileReader(new File(nameOfFile)));
            //private String userCommand;
            String[] finalUserCommand;
            String command;
            while((command = reader.readLine()) != null) {
                finalUserCommand = command.trim().toLowerCase().split(" ", 2);
                switch (finalUserCommand[0]) {
                    case "help":
                        help();
                        break;
                    case "info":
                        info();
                        break;
                    case "show":
                        show();
                        break;
                    case "add":
                        add();
                        break;
                    case "update_by_id":
                        update_by_id(finalUserCommand[1]);
                        break;
                    case "remove_by_id":
                        remove_by_id(finalUserCommand[1]);
                        break;
                    case "clear":
                        clear();
                        break;
                    case "save":
                        save();
                        break;
                    case "execute_script":
                        System.out.println("This script cannot to contain this command.");
                        break;
                    case "exit":
                        exit();
                    case "add_if_min":
                        add_if_min(new Person(receiveId(), receiveName(), receiveCoordinates(), returnDate(),
                                receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(),
                                receiveLocation()));
                        break;
                    case "remove_greater":
                        remove_greater(receiveHeight());
                        break;
                    case "remove_lower":
                        remove_lower(receiveHeight());
                        break;
                    case "sum_of_height":
                        sum_of_height();
                        break;
                    case "group_counting_by_nationality":
                        group_counting_by_nationality();
                        break;
                    case "count_greater_than_nationality":
                        count_greater_than_nationality(receiveNationality());
                        break;
                    default:
                        reader.readLine();
                        break;
                }
                System.out.println("Command is ended.");
            }
            System.out.println("Commands are ended.");
            reader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found. Try again.");
        } catch (IOException ioException) {
            System.out.println("File reading exception. Try again.");
        }
    }

    /** Method for printing current date in string representation */
    public String returnDate() {
        return ZonedDateTime.now().toString();
    }
}