package sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuBoardDaoFactory {
    private static Logger logger = LoggerFactory.getLogger(SudokuBoardDaoFactory.class);


    public SudokuBoardDaoFactory() {
        logger.info("Stowrzono {}", SudokuBoardDaoFactory.class);

    }

    public Dao getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public Dao<SudokuBoard> getDatabaseDao(String filename) {
        return new JdbcSudokuBoardDao(filename);
    }
}
