package sudoku;

import java.io.IOException;

public class MainException extends IOException {
    public MainException() {
    }

    public MainException(String message) {
        super(message);
    }

    public MainException(String message, Throwable cause) {
        super(message, cause);
    }

    public MainException(Throwable cause) {
        super(cause);
    }
}
