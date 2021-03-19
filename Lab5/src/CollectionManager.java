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

public class CollectionManager {

    private HashSet<data.Person> persons;
    private File xmlCollection;
    private Date initializationDate;
    private boolean wasStart;
    private final HashMap<String, String> commandsInfo;
    private int[] ids;
    String hash;

    {
        wasStart = false;
        persons = new HashSet<>();

        // making a manual
        commandsInfo = new HashMap<>();
        commandsInfo.put("help", " - display help for available commands");
        commandsInfo.put("info", " - print all elements in string representation to standard output");
        commandsInfo.put("add {element}", " - add new element to the collection");
        commandsInfo.put("update_by_id id {элемент}", " - update the element`s value, whose ID is equal to the given");
        commandsInfo.put("remove_by_id", " - remove an element from the collection by its ID");
        commandsInfo.put("clear", " - clear the collection");
        commandsInfo.put("save", " - save the collection to file");
        commandsInfo.put("execute_script filename", " - read and execute a script from specified file");
        commandsInfo.put("exit", " - end the program (without saving to file)");
        commandsInfo.put("add_if_min {element}", " - add new element to the collection, if it`s value less, " +
                "than smallest element of this collection");
        commandsInfo.put("remove_greater {element}", " - remove from the collection all elements greater than the specified one");
        commandsInfo.put("remove_lower {element}", " - remove from the collection all elements less than the specified one");
        commandsInfo.put("sum_of_height", " - print the sum of the values of the height field for all elements of the collection");
        commandsInfo.put("group_counting_by_nationality", " - group collection items by field value " +
                "nationality, display the number of items in each group");
        commandsInfo.put("count_greater_then_nationality nationality", " - print the number of elements, value" +
                "whose nationality fields are greater than the given");
    }

    // Constructor for checking a path to file existence
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

                                XMLEvent e = null;
                                // loop though the xml stream
                                int counter = 0;
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
                                String checker = "";
                                try {
                                    File myfile = new File("hash.txt");
                                    //создаем объект FileReader для объекта File
                                    FileReader fr = new FileReader(myfile);
                                    //создаем BufferedReader с существующего FileReader для построчного считывания
                                    BufferedReader reader = new BufferedReader(fr);
                                    String line = reader.readLine();
                                    checker = line;
                                    reader.close();
                                } catch (FileNotFoundException fileNotFoundException) {
                                    System.out.println("File saving critical error.");
                                } catch (IOException ioException) {
                                    System.out.println("File saving critical error.");
                                }
                                System.out.println(org.apache.commons.codec.digest.DigestUtils.md5Hex(Files.newInputStream(Paths.get(pathToFile))));
                                if (org.apache.commons.codec.digest.DigestUtils.md5Hex(Files.newInputStream(Paths.get(pathToFile))).equals(checker)) {
                                    System.out.println("Collection was loaded successfully. " + counter + " elements has been loaded.");
                                } else {
                                    System.out.println("You changed the file artificially, without using a collection manager.");
                                    System.out.println(" The file is damaged and the program cannot work.");
                                    System.out.println("Contact support (https://t.me/nesterrovv) to resolve this issue");
                                    System.exit(666);
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

    public boolean checkFile() {
        Scanner scanner = new Scanner(System.in);
        if (xmlCollection.exists()) {
            if (xmlCollection.canRead()) {
                if (xmlCollection.canWrite()) {
                    return true;
                }
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


    public void help() {
        for (Map.Entry<String, String> entry : commandsInfo.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    public void info() {
        System.out.println("Type of collection: java.util.HashSet");
        System.out.println("Initialization date: " + initializationDate);
        System.out.println("Size of the collection: " + persons.size());
        System.out.println("Collection manager is active: " + wasStart);
    }

    public void show() {
        for (Person person : persons) {
            System.out.println(person.toString() + "\n");
        }
    }

    public int receiveId() {
        return (persons.size() + 1);
    }

    public String receiveName() {
        for ( ; ; ) {
            try {
                //boolean stopFlag = false;
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a name: ");
                //scanner.hasNext();
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

    public long receiveX() {
        for ( ; ; ) {
            try {
                System.out.print("Enter X coordinate. Max value is 690. ");
                Scanner scanner = new Scanner(System.in);
                long x = scanner.nextLong();
                String xx = Long.toString(x);
                if (x > 690) {
                    System.out.println("Max value is 690. Try again. ");
                    continue;
                }
                if (xx.equals(null) || xx.equals("") ) {
                    System.out.println("This value cannot be empty. Try again. ");
                    continue;
                }
                return x;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
                continue;
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    public Float receiveY() {
        for ( ; ; ) {
            try {
                System.out.print("Enter Y coordinate. This value cannot be empty. ");
                Scanner scanner = new Scanner(System.in);
                Float y = new Float(scanner.nextDouble());
                if (y.equals("")) {
                    System.out.println("This value cannot be empty. ");
                    continue;
                }
                return y;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a float-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    public Coordinates receiveCoordinates() {
        return new Coordinates(receiveX(), receiveY());
    }

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

    public long receiveXLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Enter X coordinate of location. ");
                Scanner scanner = new Scanner(System.in);
                long xLocation = scanner.nextLong();
                return xLocation;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    public Double receiveYLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Enter Y coordinate of location: ");
                Scanner scanner = new Scanner(System.in);
                Double yLocation = new Double(scanner.nextDouble());
                if (yLocation.equals("") || yLocation.equals("\n")) {
                    System.out.println("Value cannot be empty. Try again. ");
                } else {
                    return yLocation;
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    public String receiveNameLocation() {
        for ( ; ; ) {
            try {
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

    public Location receiveLocation() {
        return new Location(receiveXLocation(), receiveYLocation(), receiveNameLocation());
    }

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

    public Person add() {
        Person newPerson = new Person(receiveId(), receiveName(), receiveCoordinates(), returnDate(),
                receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(), receiveLocation());
        persons.add(newPerson);
        if (checkFile()) {
            System.out.println("Element was adding successfully. ");
            return newPerson;
        } else {
            System.out.println("Resolve the indicated problem. Otherwise, the results of your work will not be saved. ");
            return null;
        }
    }


    public void save() {
        try {
            //Initialize the persons list
            /*
            Persons persons = new Persons();
            persons.setPersons(new ArrayList<Person>());
            //Create two persons
            long x = 12;
            Float y = new Float(12);
            long xx = 123;
            Float yy = new Float(123);
            long xxx = 21;
            Double yyy = new Double(21);
            long xxxx = 321;
            Double yyyy = new Double(321);
            Person person1 = new Person(1, "Ivan Ivanov", new Coordinates(x, y), returnDate(), 175, EyeColor.RED, HairColor.BLUE, Country.NORTH_KOREA, new Location(xx, yyy, "Russia"));
            Person person2 = new Person(2, "Petr Petrov", new Coordinates(xxx, yy), returnDate(), 185, EyeColor.BLUE, HairColor.YELLOW, Country.GERMANY, new Location(xxxx, yyyy, "USA"));
            Person person3 = add();
            //Add the employees in list
            persons.getPersons().add(person1);
            persons.getPersons().add(person2);
            persons.getPersons().add(person3);
            System.out.println("Пока нормально все");
             */
            //List<Person> newPersons = persons.toArray();
            //Persons persons = new Persons();
            //ArrayList<Person> persons2 = persons.toArray();
            //persons.setPersons(new ArrayList<Person>());
            System.out.println("=========================================================================================================================");
            Persons newPersons = new Persons();
            newPersons.setPersons(new ArrayList<Person>(persons));
            System.out.println("=========================================================================================================================");
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            System.out.println("Пока нормально все");
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Marshal the persons list in console
            System.out.println("Тут тоже все хорошо");
            jaxbMarshaller.marshal(newPersons, System.out);
            System.out.println("ПОКА ЕЩЕ ВСЕ ХОРОШО");
            //Marshal the persons list in file
            jaxbMarshaller.marshal(newPersons, xmlCollection);
            System.out.println("ПОКА ЕЩЕ ВСЕ ХОРОШО!!!!!!!!");
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

    public String hashCollection(String filename) {
        try (InputStream is = Files.newInputStream(Paths.get(filename))) {
            return org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
        } catch (IOException ioException) {
            return "File saving error. Try again.";
        }
    }

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

    public void add_if_min(Person example) {
        int counter = 0;int counter = 0;
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

    public void sum_of_height() {
        double sum = 0;
        for (Person person : persons) {
            sum += person.getHeight();
        }
        System.out.println("Sum of height values in this collection is " + sum);
    }

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

    public void clear() {
        persons.clear();
        System.out.println("Collection was cleaned successfully.");
    }

    public void count_greater_than_nationality (Country country) {
        int exampleHashcode = country.hashCode();
        int counter = 0;
        for (Person person : persons) {
            if ((person.getNationality()).hashCode() > exampleHashcode) { ;
                counter += 1;
            }
        }
        System.out.println("Operation was finished successfully. " + counter + " elements.");
    }

    public String remove_by_id(String id) {
        for (Person person : persons) {
            int intId = person.getId();
            String strId = String.valueOf(intId);
            if (strId.equals(id)) {
                persons.remove(person);
                return "Element was deleted successfully.";
            }
        }
        return "Element with this ID is not defined.";
    }

    public String update_by_id(String id) {
        for (Person person : persons) {
            int intId = person.getId();
            String strId = String.valueOf(intId);
            if (strId.equals(id)) {
                persons.remove(person);
                Person updatedPerson = new Person(intId, receiveName(), receiveCoordinates(), person.returnCreationDate(),
                        receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(), receiveLocation());
                return "Element was updated successfully.";
            }
        }
        return "Element with this ID is not defined.";
    }

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

    public void execute_script(String nameOfFile) {
        try {
            System.out.println("WARNING. To avoid recursion, your file cannot contain execute script commands.");
            System.out.print("Enter full path to file with executable script here: ");
            BufferedReader reader = new BufferedReader(new FileReader(nameOfFile));
            /*
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
*/
            String[] line = null;
            try {
                while ((line[0] = reader.readLine()) != null) {
                    switch (line[0]) {
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
                            update_by_id(String.valueOf(receiveId()));
                            break;
                        case "remove_by_id":
                            remove_by_id(String.valueOf(receiveId()));
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
                                    receiveHeight(), receiveEyeColor(), receiveHairColor(), receiveNationality(), receiveLocation()));
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
                }
            } catch (NullPointerException nullPointerException) {
                System.out.println("File is empty. Try again.");
            }
            reader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found. Try again.");
        } catch (IOException ioException) {
            System.out.println("File reading exception. Try again.");
        }
    }

    public String returnDate() {
        return ZonedDateTime.now().toString();
    }

}