package server.commands.receiveCommands;

import server.commands.AbstractCommand;
import server.data.Country;
import server.serverCode.CollectionManager;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReceiveNationalityCommand extends AbstractCommand {

    public ReceiveNationalityCommand(CollectionManager manager) {
        super(manager);
        setDescription("Allows to receive field hairColor for object of class Person");
    }

    @Override
    public synchronized String execute() {
        for ( ; ; ) {
            try {
                System.out.println("Choose variant of hair color. Enter the number corresponding to the desired option. ");
                System.out.print("Variants: 1 - GERMANY, 2 - CHINA, 3 - NORTH KOREA. Enter 1, 2 or 3: ");
                Scanner scanner = new Scanner(System.in);
                int nationalityChoice = scanner.nextInt();
                switch (nationalityChoice) {
                    case 1:
                        return String.valueOf(Country.GERMANY);
                    case 2:
                        return String.valueOf(Country.CHINA);
                    case 3:
                        return String.valueOf(Country.NORTH_KOREA);
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
}

