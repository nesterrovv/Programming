package server.commands.receiveCommands;

import server.commands.AbstractCommand;
import server.serverCode.CollectionManager;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReceiveHeightCommand extends AbstractCommand {

    public ReceiveHeightCommand(CollectionManager manager) {
        super(manager);
        setDescription("Allows to receive field height of class Coordinates for object of class Person");
    }

    @Override
    public synchronized String execute() {
        for ( ; ; ) {
            try {
                System.out.print("Enter height. Value must be greater than 0. ");
                Scanner scanner = new Scanner(System.in);
                long height = scanner.nextLong();
                if (height <= 0) {
                    System.out.println("This value must be greater than 0. Try again. ");
                    continue;
                }
                return String.valueOf(height);
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }
}

