package server.commands.receiveCommands;

import server.commands.AbstractCommand;
import server.serverCode.CollectionManager;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReceiveYLocationCommand extends AbstractCommand {

    public ReceiveYLocationCommand(CollectionManager manager) {
        super(manager);
        setDescription("Allows to receive field Y of class Location for object of class Person");
    }

    @Override
    public synchronized String execute() {
        for ( ; ; ) {
            try {
                System.out.print("Enter Y coordinate of location: ");
                Scanner scanner = new Scanner(System.in);
                return String.valueOf(scanner.nextDouble());
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }
}