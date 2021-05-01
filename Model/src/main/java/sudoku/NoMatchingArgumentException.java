package sudoku;

public class NoMatchingArgumentException extends IllegalArgumentException {

    public NoMatchingArgumentException() {
    }

    public NoMatchingArgumentException(String s) {
        super(s);
    }

    public NoMatchingArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMatchingArgumentException(Throwable cause) {
        super(cause);
    }

}
