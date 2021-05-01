package sudoku;

public class BadSizeException extends IllegalArgumentException {
    public BadSizeException() {
    }

    public BadSizeException(String s) {
        super(s);
    }

    public BadSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadSizeException(Throwable cause) {
        super(cause);
    }
}
