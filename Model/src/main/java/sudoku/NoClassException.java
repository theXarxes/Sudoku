package sudoku;

import java.io.IOException;

public class NoClassException extends MainException {
    public NoClassException() {
    }

    public NoClassException(String s) {
        super(s);
    }

    public NoClassException(String s, Throwable ex) {
        super(s, ex);
    }
}
