import data.Person;

import java.util.*;

public class Commander {

    private final CollectionManager collectionManager;
    private String userCommand;
    private String[] finalUserCommand;

    {
        userCommand = "";
    }

    public Commander(CollectionManager manager) {
        this.collectionManager = manager;
    }

    public void interactiveMod() {
        try {
            try (Scanner commandReader = new Scanner(System.in)) {
                while (!userCommand.equals("exit")) {
                    System.out.print("Enter a command: ");
                    userCommand = commandReader.nextLine();
                    finalUserCommand = userCommand.trim().toLowerCase().split(" ", 2);
                    try {
                        switch (finalUserCommand[0]) {
                            case "":
                                break;
                            case "help":
                                collectionManager.help();
                                break;
                            case "info":
                                collectionManager.info();
                                break;
                            case "show":
                                collectionManager.show();
                                break;
                            case "add":
                                collectionManager.add();
                                break;
                            case "update_by_id":
                                collectionManager.update_by_id(finalUserCommand[1]);
                                break;
                            case "remove_by_id":
                                collectionManager.remove_by_id(finalUserCommand[1]);
                                break;
                            case "clear":
                                collectionManager.clear();
                                break;
                            case "save":
                                collectionManager.save();
                                break;
                            case "execute_script":
                                collectionManager.execute_script(finalUserCommand[1]);
                                break;
                            case "exit":
                                collectionManager.exit();
                                break;
                            case "add_if_min":
                                System.out.println("Enter characteristics of element, which will be compared with elements in collection.");
                                collectionManager.add_if_min(new Person(collectionManager.receiveId(), collectionManager.receiveName(),
                                        collectionManager.receiveCoordinates(), collectionManager.returnDate(),
                                        collectionManager.receiveHeight(), collectionManager.receiveEyeColor(),
                                        collectionManager.receiveHairColor(), collectionManager.receiveNationality(),
                                        collectionManager.receiveLocation()));
                                break;
                            case "remove_greater":
                                System.out.println("Enter characteristics of element, which will be compared with elements in collection.");
                                collectionManager.remove_greater(collectionManager.receiveHeight());
                                break;
                            case "remove_lower":
                                System.out.println("Enter characteristics of element, which will be compared with elements in collection.");
                                collectionManager.remove_lower(collectionManager.receiveHeight());
                                break;
                            case "sum_of_height":
                                collectionManager.sum_of_height();
                                break;
                            case "group_counting_by_nationality":
                                collectionManager.group_counting_by_nationality();
                                break;
                            case "count_greater_than_nationality":
                                System.out.println("Enter nationality, which will be compared with element`s nationality.");
                                collectionManager.count_greater_than_nationality(collectionManager.receiveNationality());
                                break;
                            default:
                                System.out.println("Unknown command. Write help for help.");
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Argument of command is absent. Write help for help.");
                    }
                }
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Program will be finished now.");
            System.exit(1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commander)) return false;
        Commander commander = (Commander) o;
        return Objects.equals(commander, commander.collectionManager);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(collectionManager, userCommand);
        result = 42 * result + Arrays.hashCode(finalUserCommand);
        return result;
    }
}