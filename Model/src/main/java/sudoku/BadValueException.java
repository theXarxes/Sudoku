package sudoku;

public class BadValueException extends IllegalArgumentException {
    public BadValueException() {
    }

    public BadValueException(String s) {
        super(s);
    }

    public BadValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadValueException(Throwable cause) {
        super(cause);
    }
}
