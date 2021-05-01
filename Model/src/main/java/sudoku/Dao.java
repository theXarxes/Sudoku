package sudoku;

import java.io.IOException;
import java.sql.SQLException;

public interface Dao<T> extends AutoCloseable {
    T read() throws IOException, ClassNotFoundException, SQLException;

    void write(T object) throws IOException, SQLException, ClassNotFoundException;
}
