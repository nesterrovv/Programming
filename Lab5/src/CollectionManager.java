import javax.xml.bind.*;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import data.*;

public class CollectionManager {

    private HashSet<data.Person> persons;
    private File xmlCollection;
    private final Date initializationDate;
    //private JAXB serializer;
    private boolean wasStart;
    private final HashMap<String, String> commandsInfo;
    //private JAXBContext jaxbContext;
    private HashSet<Person> newCollection;

    {
        wasStart = false;
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
    public CollectionManager() {
        Scanner scanner = new Scanner(System.in);
        for( ; ; ) {
            System.out.print("Enter a full path to XML file with collection: ");
            String pathToFile = scanner.nextLine();
            File file = new File(pathToFile);
            if (file.exists()) {
                if (file.canRead()) {
                    if (file.canWrite()) {
                        this.xmlCollection = new File(pathToFile);
                        this.initializationDate = new Date();
                        this.wasStart = true;
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
    }

    public void help() {
        System.out.println("Commands: " + commandsInfo.keySet() + "\nman {command} for reference.");
    }

    public void man(String arg) {
        System.out.println(commandsInfo.get(arg));
    }

    public void show() {
        for (Person person : persons) {
            System.out.println(person.toString());
        }
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
                System.out.println(x);
                if (x > 690) {
                    System.out.println("Max value is 690. Try again.");
                    continue;
                }
                if (xx.equals(null) || xx.equals("") ) {
                    System.out.println("This value cannot be empty. Try again.");
                    continue;
                }
                return x;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number. Try again.");
                continue;
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(1);
            }
        }
    }

    public Float receiveY() {
        for ( ; ; ) {
            try {
                System.out.print("Enter Y coordinate. This value cannot be empty.");
                Scanner scanner = new Scanner(System.in);
                Float y = new Float(scanner.nextDouble());
                if (y.equals("")) {
                    System.out.println("This value cannot be empty.");
                    continue;
                }
                return y;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
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
                System.out.print("Enter height. Value must be greater than 0.");
                Scanner scanner = new Scanner(System.in);
                long height = scanner.nextLong();
                if (height <= 0) {
                    System.out.println("This value must be greater than 0. Try again.");
                    continue;
                }
                return height;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(1);
            }
        }
    }

    public long receiveXLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Enter X coordinate of location.");
                Scanner scanner = new Scanner(System.in);
                long xLocation = scanner.nextLong();
                return xLocation;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
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
                if (yLocation.equals("")) {
                    System.out.println("Value cannot be empty. Try again.");
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
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
                    System.out.println("This value cannot be empty. Try again");
                }
                return nameLocation;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
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
                System.out.println("Choose variant of eye color. Enter the number corresponding to the desired option.");
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
                System.out.println("You should to enter 1, 2 or 3. Try again.");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
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
                System.out.println("You should to enter 1, 2 or 3. Try again.");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(1);
            }
        }
    }

    public Country receiveNationality() {
        for ( ; ; ) {
            try {
                System.out.println("Choose variant of hair color. Enter the number corresponding to the desired option.");
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
                System.out.println("You should to enter 1, 2 or 3. Try again.");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(1);
            }
        }
    }

    public void add() {
            Person newPerson = new Person(receiveName(), receiveCoordinates(), ZonedDateTime.now(), receiveHeight(),
                    receiveEyeColor(), receiveHairColor(), receiveNationality(), receiveLocation());
    }

    public void marshalElement(Person person) {
        persons.add(person);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(persons, new File("employees.xml"));
            System.out.println("Collection are saved to file successfully.");
        }
        catch (JAXBException jaxbException) {
            System.out.println("XML syntax error");
        }
    }


}