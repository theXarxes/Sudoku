package sudoku;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class SpyTest {
    public SpyTest() {
    }
    @Test
    public void spyTest() {
        SimpleSudokuSolver solver = new SimpleSudokuSolver();
        SudokuBoard su = new SudokuBoard(solver);
        Spy s = new Spy(su);
        su.solveGame();
        su.notifyObserver();
        su.set(8,8,3);
        s.update();
        assertEquals(3,s.inform());

    }

}
