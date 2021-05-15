package server.commands.receiveCommands;

import server.commands.AbstractCommand;
import server.data.EyeColor;
import server.serverCode.CollectionManager;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReceiveEyeColorCommand extends AbstractCommand {

    public ReceiveEyeColorCommand(CollectionManager manager) {
        super(manager);
        setDescription("Allows to receive field eyeColor for object of class Person");
    }

    @Override
    public synchronized String execute() {
        for ( ; ; ) {
            try {
                System.out.println("Choose variant of eye color. Enter the number corresponding to the desired option. ");
                System.out.print("Variants: 1 - GREEN, 2 - RED, 3 - BLUE. Enter 1, 2 or 3: ");
                Scanner scanner = new Scanner(System.in);
                int eyeChoose = scanner.nextInt();
                switch (eyeChoose) {
                    case 1:
                        return String.valueOf(EyeColor.GREEN);
                    case 2:
                        return String.valueOf(EyeColor.RED);
                    case 3:
                        return String.valueOf(EyeColor.BLUE);
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