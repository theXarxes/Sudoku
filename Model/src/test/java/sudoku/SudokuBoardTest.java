package sudoku;


import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    private SudokuBoard board;
    private SimpleSudokuSolver solver;
    public SudokuBoardTest() {

    }

    @BeforeEach
    public void testPreparation() {
        solver = new SimpleSudokuSolver();
        board=new SudokuBoard(solver);
        board.solveGame();

    }

    @AfterEach
    public void testEnd() {
        board.clearBoard();
    }

    @Test
    public void SudokuBoardTest_vol1() {

        //drawBoardTest(drawBoard zawarty w solveGame)
        assertTrue(board.drawBoard());
        //clearBordTest
        board.clearBoard();
        int flag=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board.get(i,j) != 0){
                    flag=1;
                }
            }
        }
        assertEquals(0, flag);
    }

    @Test
    public void SudokuBoardTest_vol2() {
        assertTrue(board.getColumn(2).verify());
        assertTrue(board.getBox(1,3).verify());
        assertTrue(board.getRow(1).verify());
    }

    @Test
    public void checkBoardTest() {
        assertTrue(board.uselessFunction());
    }

    @Test
    public void SudokuSpyTest() {
        Spy s = new Spy(board);
        board.attach(s);
        assertEquals(1,board.getObserverset().size());
        board.dettach(s);
        assertEquals(0,board.getObserverset().size());
    }

    @Test
    public void equalsTest(){
        SudokuBoard sp = new SudokuBoard(solver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sp.set(i,j,board.get(i,j));
            }
        }
        assertTrue(board.equals(sp));
    }

    @Test
    public void toStringTest(){
        System.out.println(board.toString());
    }

    @Test
    public void hashCodeTest(){
        SudokuBoard sp = new SudokuBoard(solver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sp.set(i,j,board.get(i,j));
            }
        }
        assertEquals(board.hashCode(),sp.hashCode());
    }

    @Test
    public void cloneTest(){
        SudokuBoard clone=new SudokuBoard(null);
        clone=board.clone();
        assertEquals(clone,board);
        SudokuField clonef= new SudokuField();
        SudokuField normalf=new SudokuField();
        normalf.setFieldValue(3);
        clonef=normalf.clone();
        assertEquals(clonef,normalf);
    }

    @Test
    public void glupotyTest(){
        String napis =board.glupoty();
        System.out.println(napis);
    }

    @Test
    public void compareTest(){
        SudokuField f = new SudokuField();
        SudokuField f1 = new SudokuField();
        f.setFieldValue(1);
        f1.setFieldValue(2);
        assertEquals(1,f.compareTo(f1));
    }

    @Test
    public void languageHelperTest(){
        LanguageHelper helper = new LanguageHelper();
        helper.setLang("Polski");
        assertEquals("Polski",helper.getLang());
        assertEquals("Zle wartosci pol", LanguageHelper.fabric(1));
    }

}
