package sudoku;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SudokuRow extends SudokuVerify implements Serializable {
    private  static Logger logger = LoggerFactory.getLogger(SudokuRow.class);


    public SudokuRow() {
        logger.info("Stowrzono {}", SudokuRow.class);
    }

    @Override
    protected SudokuRow clone() {
        return (SudokuRow) super.clone();
    }

}
