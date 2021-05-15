package server.commands.receiveCommands;

import server.commands.AbstractCommand;
import server.data.HairColor;
import server.serverCode.CollectionManager;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReceiveHairColorCommand extends AbstractCommand {

    public ReceiveHairColorCommand(CollectionManager manager) {
        super(manager);
        setDescription("Allows to receive field hairColor for object of class Person");
    }

    @Override
    public synchronized String execute() {
        for ( ; ; ) {
            try {
                System.out.println("Choose variant of hair color. Enter the number corresponding to the desired option.");
                System.out.print("Variants: 1 - BLUE, 2 - YELLOW, 3 - ORANGE. Enter 1, 2 or 3: ");
                Scanner scanner = new Scanner(System.in);
                int hairChoice = scanner.nextInt();
                switch (hairChoice) {
                    case 1:
                        return String.valueOf(HairColor.BLUE);
                    case 2:
                        return String.valueOf(HairColor.YELLOW);
                    case 3:
                        return String.valueOf(HairColor.ORANGE);
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
}

