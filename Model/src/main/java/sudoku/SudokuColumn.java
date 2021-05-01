package sudoku;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SudokuColumn extends SudokuVerify implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(SudokuColumn.class);


    public SudokuColumn() {
        logger.info("Stowrzono {}", SudokuColumn.class);

    }

    @Override
    protected SudokuColumn clone() {
        return (SudokuColumn) super.clone();
    }

}
