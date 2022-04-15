package application.exception;

public class NonExistingEntityException extends Exception {
    public NonExistingEntityException() {
    }

    public NonExistingEntityException(String message) {
        super(message);
    }

    public NonExistingEntityException(Throwable cause) {
        super(cause);
    }

    public NonExistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
