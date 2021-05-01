package sudoku;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SudokuBox extends SudokuVerify implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(SudokuBox.class);


    public SudokuBox() {
        logger.info("Stowrzono {}", SudokuBox.class);

    }


    @Override
    protected SudokuBox clone() {
        return (SudokuBox) super.clone();
    }
}
