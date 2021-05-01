package sudoku;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private static Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);
    private final String name;

    public FileSudokuBoardDao(String name) {
        this.name = name;
        logger.info("Stowrzono {}", FileSudokuBoardDao.class);
    }

    @Override
    public SudokuBoard read() throws FileException {

        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(this.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return (SudokuBoard) input.readObject();
        } catch (ClassNotFoundException e) {
            throw new FileException(e);
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    @Override
    public void write(SudokuBoard b) throws FileException {

        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream(this.getName()));
        } catch (IOException e) {
            throw new FileException(LanguageHelper.fabric(2),e);
        }

        try {
            output.writeObject(b);
        } catch (IOException e) {
            throw new FileException(LanguageHelper.fabric(2),e);
        }
    }




    public String getName() {
        return name;
    }

    @Override
    public void close() {
        logger.info("Poprawnie zamknięto plik");
    }
}
