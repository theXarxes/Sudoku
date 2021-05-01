package sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Spy implements Observer {
    private static Logger logger = LoggerFactory.getLogger(Spy.class);
    private SudokuBoard board;
    private int change;

    public Spy(SudokuBoard board) {
        this.board = board;
        this.change = board.getNewestAction();
        logger.info("Stowrzono {}", Spy.class);
    }

    @Override
    public void update() {
        change = board.getNewestAction();
    }

    public int inform() {
        return change;
    }
}
