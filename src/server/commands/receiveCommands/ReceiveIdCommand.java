package server.commands.receiveCommands;

import server.commands.AbstractCommand;
import server.serverCode.CollectionManager;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;

public class ReceiveIdCommand extends AbstractCommand {

    public ReceiveIdCommand(CollectionManager manager) {
        super(manager);
        setDescription("Allows to receive field ID for object of class Person");
    }

    @Override
    public synchronized String execute() {
        for ( ; ; ) {
            try {
                return String.valueOf((new Random()).nextInt(Integer.MAX_VALUE));
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be integer-type of number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(1);
            }
        }
    }
}
