package server.serverCode;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import server.data.*;

/**
 * @author Ivan Nesterov
 * @version 1.5
 * Class which realised user`s commands
 */
public class CollectionManager {

    /** HashSet collection for keeping a collection as java-object */
    private final HashSet<server.data.Person> persons;
    /** Field used for saving collection into xml file */
    private File xmlCollection;
    /** Field for saving date of initialization thw collection */
    private ZonedDateTime initializationDate;
    /** Field for checking the program was started */
    private boolean wasStart;
    /** HashMap collection for making a manual */
    private final HashMap<String, String> commandsInfo;
    {
        wasStart = false;
        persons = new HashSet<>();

        // Making a manual
        commandsInfo = new HashMap<>();
        commandsInfo.put("help", " - display help for available commands");
        commandsInfo.put("info", " - print all elements in string representation to standard output");
        commandsInfo.put("add", " - add new element to the collection");
        commandsInfo.put("update_by_id element", " - update the element`s value, whose ID is equal to the ID of given element." +
                " You should to enter ID and XML-string person after entering a command .");
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
    public CollectionManager(String arg) {
        //Scanner scanner = new Scanner(System.in);
        try {
            //String pathToFile = arg;
            if (checkFile(arg)) {
                try {
                    final QName qName = new QName("person");
                    InputStream inputStream = new FileInputStream(new File(arg));
                    // create xml event reader for input stream
                    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
                    XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);
                    // initialize jaxb
                    JAXBContext context = JAXBContext.newInstance(Person.class);
                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    XMLEvent e;
                    // Field for counting amount of downloaded elements
                    int counterGood = 0;
                    int counterBad = 0;
                    // Loop for unmarshalling the collection
                    while ((e = xmlEventReader.peek()) != null) {
                        // check the event is a Document start element
                        if (e.isStartElement() && ((StartElement) e).getName().equals(qName)) {
                            // unmarshall the document
                            Person unmarshalledPerson = unmarshaller.unmarshal(xmlEventReader, Person.class).getValue();
                            Coordinates newCoordinates = unmarshalledPerson.getCoordinates();
                            Location newLocation = unmarshalledPerson.getLocation();
                            if (unmarshalledPerson.getId() != 0 && !unmarshalledPerson.getName().equals(null) &&
                                    !newCoordinates.getX().equals(null) && !newCoordinates.getY().equals(null) &&
                                    !unmarshalledPerson.returnCreationDate().equals(null) &&
                                    unmarshalledPerson.getHeight() > 0 &&
                                    !unmarshalledPerson.getEyeColor().equals(null) &&
                                    !unmarshalledPerson.getHairColor().equals(null) &&
                                    !unmarshalledPerson.getNationality().equals(null) &&
                                    !newLocation.getX().equals(null) &&
                                    !newLocation.getY().equals(null) && !newLocation.getName().equals(null)) {
                                        persons.add(unmarshalledPerson);
                                        counterGood += 1;
                                } else counterBad += 1;
                            } else {
                                xmlEventReader.next();
                            }
                        }
                        System.out.println("Collection was loaded successfully. " + counterGood + " elements has been loaded.");
                        System.out.println("Amount of elements which contains invalid values and has not been loaded: " + counterBad);
                        xmlCollection = new File(arg);
                        wasStart = true;
                        initializationDate = ZonedDateTime.now();
                    } catch (JAXBException jaxbException) {
                        System.out.println("XML syntax error.");
                    } catch (FileNotFoundException fileNotFoundException) {
                        System.out.println("File not found.");
                        System.exit(1);
                    } catch (XMLStreamException xmlStreamException) {
                        xmlCollection = new File(arg);
                        System.out.println("Empty collection was loaded successfully.");
                    }
                } else {
                    System.out.println("Invalid path to file. Try again.");
                    System.exit(1);
                }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Program will be finished now.");
            System.exit(0);
        }
    }

    /**
     * Class which check file is existed, and can be readable and writeable.
     * @return readiness status
     */
    public boolean checkFile(String pathToFile) {
        File checkingFile = new File(pathToFile);
        if (!checkingFile.exists()) {
            //System.out.println("File not found. Try again.");
            return false;
        }
        if (!checkingFile.canRead()) {
            System.out.println("File cannot be readable. You should to have this permission.");
            return false;
        }
        if (!checkingFile.canWrite()) {
            System.out.println("File cannot be writeable. You should to have this permission.");
            return false;
        }
        return true;
    }

    /** Method for printing manual for user */
    public void help() {
        for (Map.Entry<String, String> entry : commandsInfo.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    /** Method for printing information about the collection */
    public String info() {
        StringBuilder result = new StringBuilder();
        result.append("Type of collection: java.util.HashSet" + "\n");
        result.append("Initialization date: " + initializationDate + "\n");
        result.append("Amount of elements in the collection: " + persons.size() + "\n");
        result.append("Collection manager is active: " + wasStart);
        return result.toString();
    }

    /** Method for printing collection elements into the string representation */
    public ArrayList<String> show() {
        ArrayList<String> printedPersons = new ArrayList<>();
        for (Person person : persons) {
            printedPersons.add(person.toString() + "\n");
        }
        return printedPersons;
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
       /*
        Person newPerson = new Person(receiveId(), receiveName(), receiveCoordinates(), returnDate(),
                receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(), receiveLocation());
        persons.add(person);
        */
        Person newPerson = new Person(receiveId(), receiveName(), receiveCoordinates(), returnDate(),
                receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(), receiveLocation());
        persons.add(newPerson);
        //return "Element has been added successfully.";
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
        } catch (JAXBException jaxbException) {
            System.out.println("XML syntax error. Try again. ");
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
    public double sum_of_height() {
        double sum = 0;
        for (Person person : persons) {
            sum += person.getHeight();
        }
        return sum;
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
    public String count_greater_than_nationality (Country country) {
        int exampleHashcode = country.hashCode();
        int counter = 0;
        for (Person person : persons) {
            if ((person.getNationality()).hashCode() > exampleHashcode) {
                counter += 1;
            }
        }
        return "Operation was finished successfully. " + counter + " elements.";
    }

    /** Method for removing the element by it`s ID */
    public void remove_by_id(String id) {
        try {
            for (Person person : persons) {
                int intId = person.getId();
                String strId = String.valueOf(intId);
                if (strId.equals(id)) {
                    persons.remove(person);
                    System.out.println("Element was deleted successfully.");
                }
            }
            System.out.println("Element with this ID is not defined.");
        } catch (ConcurrentModificationException concurrentModificationException) {
            System.out.println("Command is complited.");
        }
    }

    /** Method for updating the element by it`s ID */
    public String update_by_id(String id) {
        for (Person person : persons) {
            int intId = person.getId();
            String strId = String.valueOf(intId);
            if (strId.equals(id)) {
                persons.remove(person);
                Person updatedPerson = new Person(intId, receiveName(), receiveCoordinates(), person.returnCreationDate(),
                        receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(), receiveLocation());
                persons.add(updatedPerson);
                return "Element was updated successfully.";
            }
        }
    return null;
    }

    /** Method for counting amount and grouping elements by it`s nationality field */
    public String group_counting_by_nationality() {
        int chinaCounter = 0;
        int germanyCounter = 0;
        int northKoreaCounter = 0;
        for (Person person : persons) {
            switch (person.getNationality()) {
                case CHINA:
                    chinaCounter += 1;
                    break;
                case GERMANY:
                    germanyCounter += 1;
                    break;
                case NORTH_KOREA:
                    northKoreaCounter += 1;
                    break;
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Elements of this collection were grouped by nationality." + "\n");
        result.append("First group: China. Amount of elements: " + chinaCounter + "\n");
        result.append("Second group: Germany. Amount of elements: " + germanyCounter + "\n");
        result.append("Third group: North Korea. Amount of elements: " + northKoreaCounter);
        return result.toString();
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
                        //
                        //
                        //
                        //
                        // add();
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

    public HashSet<Person> getPersons() {
        return persons;
    }

    public HashMap getInfoCommands() {
        return commandsInfo;
    }
}