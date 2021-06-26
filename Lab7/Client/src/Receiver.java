import data.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Receiver {

    /**
     * Method for receiving ID of element
     * @return int ID
     */
    public int receiveId() {
        for ( ; ; ) {
            try {
                return (int) ((Math.random() * (Integer.MAX_VALUE - Integer.MIN_VALUE)) + Integer.MIN_VALUE);
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be integer-type of number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving name of element
     * @return String name
     */
    public String receiveName() {
        for ( ; ; ) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a name: ");
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

    /**
     * Method for receiving x-coordinate of element
     * @return long x
     */
    public long receiveX() {
        for ( ; ; ) {
            try {
                System.out.print("Enter X coordinate. Max value is 690. ");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Value cannot be empty. ");
                long x = scanner.nextLong();
                System.out.print("Value cannot be empty. ");
                String xx = Long.toString(x);
                if (x > 690) {
                    System.out.println("Max value is 690. Try again. ");
                    continue;
                }
                if (xx.equals("") ) {
                    System.out.println("This value cannot be empty. Try again. ");
                    continue;
                }
                return x;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving y-coordinate of element
     * @return Float y
     */
    public Float receiveY() {
        for ( ; ; ) {
            try {
                System.out.print("Enter Y coordinate. This value cannot be empty. ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextFloat();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a float-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /** Method for making coordinates by using methods receiveX() and receiveY() */
    public Coordinates receiveCoordinates() {
        return new Coordinates(receiveX(), receiveY());
    }

    /**
     * Method for receiving height of element
     * @return long height
     */
    public long receiveHeight() {
        for ( ; ; ) {
            try {
                System.out.print("Enter height. Value must be greater than 0. ");
                Scanner scanner = new Scanner(System.in);
                long height = scanner.nextLong();
                if (height <= 0) {
                    System.out.println("This value must be greater than 0. Try again. ");
                    continue;
                }
                return height;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving x-coordinate of location of element
     * @return long xLocation
     */
    public long receiveXLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Enter X coordinate of location. ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextLong();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving y-coordinate of element
     * @return Double yLocation
     */
    public Double receiveYLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Enter Y coordinate of location: ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextDouble();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving name of element`s location
     * @return String nameLocation
     */
    public String receiveNameLocation() {
        for ( ; ; ) {
            try {
                System.out.println("Attention! If name will be so long, you may lose some part of this information");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a name of location: ");
                String nameLocation = scanner.nextLine().trim();
                if (nameLocation.equals("")) {
                    System.out.println("This value cannot be empty. Try again. ");
                }
                return nameLocation;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a string. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving Location field by using methods receiveXLocation(),
     * receiveYLocation() and receiveNameLocation()
     * @return Location location
     */
    public Location receiveLocation() {
        return new Location(receiveXLocation(), receiveYLocation(), receiveNameLocation());
    }

    /**
     * Method for receiving eye color of element
     * @return EyeColor eyeColor
     */
    public EyeColor receiveEyeColor() {
        for ( ; ; ) {
            try {
                System.out.println("Choose variant of eye color. Enter the number corresponding to the desired option. ");
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
                System.out.println("You should to enter 1, 2 or 3. Try again. ");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number (1, 2 or 3). Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving hair color of element
     * @return HairColor hairColor
     */
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
                System.out.println("You should to enter 1, 2 or 3. Try again. ");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a number (1, 2 or 3). Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully. ");
                System.exit(1);
            }
        }
    }

    /**
     * Method for receiving nationality of element
     * @return Country nationality
     */
    public Country receiveNationality() {
        for ( ; ; ) {
            try {
                System.out.println("Choose variant of hair color. Enter the number corresponding to the desired option. ");
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