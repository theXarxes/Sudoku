package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class SudokuVarifyTest {

    private SudokuBox box = new SudokuBox();
    private SudokuBox box1 = new SudokuBox();
    private SudokuRow r = new SudokuRow();
    private SudokuRow r1 = new SudokuRow();
    private SudokuColumn c = new SudokuColumn();
    private SudokuColumn c1 = new SudokuColumn();
    private Random random = new Random();

    public SudokuVarifyTest() {

    }

    @BeforeEach
    public void testPreparation() {
        for(int i = 9 ; i<9 ;i++){
            box.setVerifyBoard(i,0);
            box1.setVerifyBoard(i,0);
            r.setVerifyBoard(i,0);
            r1.setVerifyBoard(i,0);
            c.setVerifyBoard(i,0);
            c1.setVerifyBoard(i,0);
        }
    }

    @Test
    public void equalsTest(){
        assertTrue(box.equals(box1));
    }

    @Test
    public void toStringTest(){
        System.out.println(box.toString());
    }

    @Test
    public void hashCodeTest(){
        assertEquals(box.hashCode(),box1.hashCode());
    }

    @Test
    public void cloneTest(){
        box1 = box.clone();
        assertTrue(box.equals(box1));
        c1 = c.clone();
        assertTrue(c.equals(c1));
        r1 = r.clone();
        assertTrue(r.equals(r1));
    }
}
