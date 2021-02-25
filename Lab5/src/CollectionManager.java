import javax.xml.bind.*;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;
import data.*;

public class CollectionManager {

    private HashSet<data.Person> persons;
    private File xmlCollection;
    private final Date initializationDate;
    private JAXB serializer;
    private boolean wasStart;
    protected static HashMap<String, String> commandsInfo;
    private JAXBContext jaxbContext;
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
    public CollectionManager(String pathToFile) throws IOException, JAXBException {
        try {
            if (pathToFile == null) throw new FileNotFoundException();
        } catch (FileNotFoundException exception) {
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
            } else {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File not found. Check if the path is entered correctly and try again");
            System.exit(1);
        }
        this.xmlCollection = new File(pathToFile);
        this.initializationDate = new Date();
        this.wasStart = true;
    }


    // help - display help for available commands
    public void help() {
        System.out.println(commandsInfo.keySet());
        commandsInfo.values();
    }


    // Method for loading and updating collection
    public void load(String pathToFile) throws IOException {
        int beginSize = persons.size();
        try {
            if (!xmlCollection.exists()) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. Check if the path is entered correctly and try again");
            if (!wasStart) System.exit(1);
            //else return;
        }
        try {
            if (!xmlCollection.canRead() || !xmlCollection.canWrite()) throw new SecurityException();
        } catch (SecurityException ex) {
            System.out.println("The file is protected from reading or writing." + "\n" +
                    " For the program to work correctly, both permissions are required.");
            if (!wasStart) System.exit(1);
            //else return;
        }
        /*
        try {
            if (xmlCollection.length() == 0) throw new JAXBException("");
        } catch (JAXBException ex) {
            System.out.println("File is empty.");
            if (!wasStart) System.exit(1);
            //else return;
        }
         */
        try (BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream(xmlCollection)))) {
            System.out.println("Collection is loading..." + "\n" +
                    " Path to this collection: " + xmlCollection.getAbsolutePath());
            String nextLine;
            StringBuilder result = new StringBuilder();
            while ((nextLine = inputStreamReader.readLine()) != null) {
                result.append(nextLine);
            }
            //Type collectionType = new TypeToken<LinkedList<Person>>() {
            //}.getType();
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
                Unmarshaller un = jaxbContext.createUnmarshaller();
                HashSet<Person> newCollection = (HashSet<Person>) un.unmarshal(new File(pathToFile));
            } catch (JAXBException ex) {
                System.out.println("XML syntax error. Failed to load collection.");
                System.exit(1);
            }
            System.out.println("Collection was loaded successfully.");
        }
        //return null;
    }


    public void save(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(persons, new File(filePath));
            System.out.println("Changes saved.");
        } catch (JAXBException e) {
            System.out.println("XML syntax error. Failed to save collection to file.");
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        return "Type of collection:: " + persons.getClass() +
                "\n" + "Initialization date: " + initializationDate +
                "\n" + "Amount of elements: " + persons.size();
    }

    public void show() {
        if (persons.size() != 0) {
            for (Person person : persons) {
                System.out.println(person.toString());
            }
        } else System.out.println("Collection is empty.");
    }

    public void add() {
        int maxInt = 2147483647;
        //int id = (int) (Math.random() * ++maxInt);

        String thisName = null;
        long thisX = 0;
        Float thisY = null;
        long thisHeight = 0;
        EyeColor thisEyeColor = null;
        HairColor thisHairColor = null;
        Country thisNationality = null;
        long thisXLocation = 0;
        Double thisYLocation = null;
        String thisNameLocation = null;

        boolean flagThisName = false;
        boolean flagThisX = false;
        boolean flagThisY = false;
        boolean flagThisHeight = false;
        boolean flagThisEyeColor = false;
        boolean flagThisHairColor = false;
        boolean flagThisNationality = false;
        boolean flagThisXLocation = false;
        boolean flagThisYLocation = false;
        boolean flagThisNameLocation = false;

        System.out.print("Enter a name: ");
        Scanner enterName = new Scanner(System.in);
        if (enterName.hasNextLine()) {
            String localName = enterName.nextLine();
            thisName = localName;
            flagThisName = true;
        } else {
            System.out.println("Name of person must be string. Try again.");
            System.exit(1);
        }
        System.out.print("Enter X coordinate. Max value is 690: ");
        Scanner enterX = new Scanner(System.in);
        if (enterX.hasNextLong()) {
            long localX = enterX.nextLong();
            thisX = localX;
            if (localX > 690) {
                System.out.println("The value is greater than the allowed. Max value is 690.");
                System.exit(1);
            } else flagThisX = true;
        } else {
            System.out.println("X coordinate must be long-type value. Try again.");
            System.exit(1);
        }
        System.out.print("Enter Y coordinate: ");
        Scanner enterY = new Scanner(System.in);
        if (enterY.hasNextFloat()) {
            Float localY = new Float(enterX.nextLong());
            thisY = localY;
            flagThisY = true;
        } else {
            System.out.println("Y coordinate must be float-type value. Try again.");
            System.exit(1);
        }
        System.out.print("Enter a height. This value must be greater than 0: ");
        Scanner enterHeight = new Scanner(System.in);
        if (enterHeight.hasNextLong()) {
            long localHeight = enterHeight.nextLong();
            thisHeight = localHeight;
            if (localHeight <= 0) {
                System.out.println("This value must be greater than 0");
                System.exit(1);
            } else flagThisHeight = true;
        } else {
            System.out.println("This value must be long-type of value. Try again.");
            System.exit(1);
        }
        System.out.println("Choose eye color. Variants: GREEN, RED, BLUE.");
        System.out.println("Please, enter an UPPERCASE value: ");
        Scanner enterEyeColor = new Scanner(System.in);
        if (enterEyeColor.hasNextLine()) {
            String localEyeColor = enterEyeColor.nextLine();
            thisEyeColor = EyeColor.valueOf(localEyeColor);
            if (!localEyeColor.equals("BLUE") && !localEyeColor.equals("GREEN") && !localEyeColor.equals("RED")) {
                System.out.println("Unacceptable color or value not in uppercase. Try again.");
                System.exit(1);
            } else flagThisEyeColor = true;
        } else {
            System.out.println("This value must be string. Try again.");
            System.exit(1);
        }
        System.out.println("Choose hair color. Variants: YELLOW, ORANGE, BLUE.");
        System.out.println("Please, enter an UPPERCASE value: ");
        Scanner enterHairColor = new Scanner(System.in);
        if (enterHairColor.hasNextLine()) {
            String localHairColor = enterHairColor.nextLine();
            thisHairColor = HairColor.valueOf(localHairColor);
            if (!localHairColor.equals("YELLOW") && !localHairColor.equals("ORANGE") && !localHairColor.equals("BLUE")) {
                System.out.println("Unacceptable color or value not in uppercase. Try again.");
                System.exit(1);
            } else flagThisHairColor = true;
        } else {
            System.out.println("This value must be string. Try again.");
            System.exit(1);
        }
        System.out.println("Choose nationality. Variants: GERMANY, CHINA, NORTH_KOREA");
        System.out.println("Please, enter an UPPERCASE value: ");
        Scanner enterNationality = new Scanner(System.in);
        if (enterNationality.hasNextLine()) {
            String localNationality = enterNationality.nextLine();
            thisNationality = Country.valueOf(localNationality);
            if (!localNationality.equals("GERMANY") && !localNationality.equals("CHINA") && !localNationality.equals("NORTH_KOREA")) {
                System.out.println("Unacceptable color or value not in uppercase. Try again.");
                System.exit(1);
            } else flagThisNationality = true;
        } else {
            System.out.println("This value must be string. Try again.");
            System.exit(1);
        }
        System.out.println("Enter X coordinate of location: ");
        Scanner enterXLocation = new Scanner(System.in);
        if (enterXLocation.hasNextLong()) {
            long localXLocation = enterXLocation.nextLong();
            thisXLocation = localXLocation;
            flagThisXLocation = true;
        } else {
            System.out.println("This value must be long-type. Try again.");
            System.exit(1);
        }
        System.out.println("Enter Y coordinate of location: ");
        Scanner enterYLocation = new Scanner(System.in);
        if (enterYLocation.hasNextLong()) {
            Double localYLocation = new Double(enterYLocation.nextDouble());
            thisYLocation = new Double(localYLocation);
            if (localYLocation == null) {
                System.out.println("This value must not be null. Try again.");
                System.exit(1);
            } else flagThisYLocation = true;
        } else {
            System.out.println("This value must be double-type. Try again.");
            System.exit(1);
        }
        System.out.println("Enter a name of location: ");
        Scanner enterNameOfLocation = new Scanner(System.in);
        if (enterNameOfLocation.hasNextLine()) {
            String localNameOfLocation = enterNameOfLocation.nextLine();
            thisNameLocation = localNameOfLocation;
            if (localNameOfLocation == null) {
                System.out.println("This value must not be null. Try again.");
            } else flagThisNameLocation = true;
        } else {
            System.out.println("This value must be string. Try again.");
            System.exit(1);
        }
        if (flagThisName && flagThisX && flagThisY && flagThisHeight && flagThisEyeColor && flagThisHairColor
                && flagThisNationality && flagThisXLocation && flagThisYLocation && flagThisNameLocation) {
            Person newPerson = new Person((int) (Math.random() * ++maxInt), thisName, new Coordinates(thisX, thisY),
                    ZonedDateTime.now(), thisHeight, thisEyeColor, thisHairColor, thisNationality,
                    new Location(thisXLocation, thisYLocation, thisNameLocation));

            persons.add(newPerson);
        }
        else {
            System.out.println("Some fields from class Person has not been initialized." + "\n" +
                               "For whom the error occurred, you can check here");
            System.out.print("Name was processed correctly: ");
            System.out.println(flagThisName);
            System.out.print("X coordinate was processed correctly: ");
            System.out.println(flagThisX);
            System.out.print("Y coordinate was processed correctly: ");
            System.out.println(flagThisY);
            System.out.print("Height was processed correctly: ");
            System.out.println(flagThisHeight);
            System.out.print("Eye color was processed correctly: ");
            System.out.println(flagThisEyeColor);
            System.out.print("Hair color was processed correctly: ");
            System.out.println(flagThisHairColor);
            System.out.print("Nationality was processed correctly: ");
            System.out.println(flagThisNationality);
            System.out.print("X coordinate of location was processed correctly: ");
            System.out.println(flagThisXLocation);
            System.out.print("Y coordinate of location was processed correctly: ");
            System.out.println(flagThisYLocation);
            System.out.print("Name of location was processed correctly: ");
            System.out.println(flagThisNameLocation);

            System.exit(1);
        }
    }

    public void update_id(int id) {
        for(Person person : persons) {
            if (person.getId() == id) {
                System.out.println("Updating this element of the collection is started.");
                
            }
            else {
                System.out.println("The collection does not contain an item with this ID");
                System.exit(1);
            }
        }
    }




}