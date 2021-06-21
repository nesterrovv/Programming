package serverCode;

public class UnknownUserException extends Exception {
    private String message;

    public UnknownUserException(String message) {
        super(message);
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}

