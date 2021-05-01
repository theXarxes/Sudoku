package sudoku;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleSudokuSolverTest {
    private SimpleSudokuSolver solver;
    private SudokuBoard board;

    public SimpleSudokuSolverTest() {

    }

    @BeforeEach
    public void testPreparation() {
        solver=new SimpleSudokuSolver();
        board=new SudokuBoard(solver);
        board.solveGame();
    }

    @AfterEach
    public void testEnd() {
        board.clearBoard();
    }


    @Test
    public void testDraw() {
        board.drawBoard();
    }

    @Test
    public void testSudoku() {
        int[][] test = new int[9][9];
         for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                test[i][j] = board.get(i, j);
            }
        }
        //test column
        int flag1 = 0;
        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 9; i++) {
                 for (int j = 0; j < 9; j++) {
                    if (test[i][k] != test[j][k]) {
                        flag1++;
                    }
                    if (i == j && test[i][k] == test[j][k]) {
                        flag1++;
                    }
                 }
            }
        }
        assertEquals(729, flag1);
        //test raw

        int flag2 = 0;
        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (test[k][i] != test[k][j]) {
                        flag2++;
                    }
                    if (i == j && test[k][i] == test[k][j]) {
                         flag2++;
                    }
                }
            }
        }
        assertEquals(729, flag2);

        int flag3 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 9; k++) {
                    for (int l = k + 1; l < 9; l++) {
                        if (test[i * 3 + (k / 3)][j * 3 + (k % 3)] ==
                            test[i * 3 + (l / 3)][j * 3 + (l % 3)]) {
                                flag3 = 1;
                                break;
                        }
                    }
                }
            }
        }
        assertEquals(0, flag3);
    }

     @Test
     public void testFillBoard() {
        SimpleSudokuSolver solver = new SimpleSudokuSolver();
        SudokuBoard b = new SudokuBoard(solver);
        b.solveGame();
        int[][] test = new int[9][9];
        for (int i = 0; i < 9; i++) {
           for (int j = 0; j < 9; j++) {
               test[i][j] = b.get(i, j);
           }
        }
        b.clearBoard();
        b.solveGame();
        int flag = 0;
        for (int i = 0; i < 9; i++) {
           for (int j = 0; j < 9; j++) {
               if (test[i][j] == b.get(i, j)) {
                   flag++;
            }
         }
         assertNotEquals(81,flag);
        }

     }

}
