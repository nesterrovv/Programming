package server.commands.receiveCommands;

import server.commands.AbstractCommand;
import server.serverCode.CollectionManager;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReceiveXCommand extends AbstractCommand {

    public ReceiveXCommand(CollectionManager manager) {
        super(manager);
        setDescription("Allows to receive field X of class Coordinates for object of class Person");
    }

    @Override
    public synchronized String execute() {
        for ( ; ; ) {
            try {
                System.out.print("Enter X coordinate. Max value is 690. ");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Value cannot be empty. ");
                long x = scanner.nextLong();
                String xx = Long.toString(x);
                if (x > 690) {
                    System.out.println("Max value is 690. Try again. ");
                    continue;
                }
                if (xx.equals("") ) {
                    System.out.println("This value cannot be empty. Try again. ");
                    continue;
                }
                return String.valueOf(x);
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be long-type number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(1);
            }
        }
    }
}

