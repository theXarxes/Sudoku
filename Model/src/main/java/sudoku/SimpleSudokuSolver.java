package sudoku;

import java.io.Serializable;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleSudokuSolver implements SudokuSolver, Serializable {

    private static Logger logger = LoggerFactory.getLogger(SimpleSudokuSolver.class);

    @Override
    public void solve(SudokuBoard board) {
        start(board);
        solve(board,0,0);
    }

    public boolean solve(SudokuBoard board, int rzad, int kolumna) {
        if (rzad == 8 && kolumna == 9) {
            return true;
        }
        if (kolumna == 9) {
            rzad++;
            kolumna = 0;
        }
        if (board.get(rzad,kolumna) != 0) {
            return solve(board, rzad, kolumna + 1);
        }
        for (int i = 1; i < 10; i++) {
            if (isSafe(board, rzad, kolumna, i)) {
                board.set(rzad,kolumna,i);
                if (solve(board, rzad, kolumna + 1)) {
                    return true;
                }
            }
            board.set(rzad,kolumna,0);
        }
        return false;
    }

    public SimpleSudokuSolver() {
        logger.info("Stowrzono {}", SimpleSudokuSolver.class);
    }

    public void start(SudokuBoard board) {
        int j = 2;
        Random rand = new Random();
        int num = rand.nextInt(9) + 1;
        board.set(0,0,num);
        do {
            num = rand.nextInt(9) + 1;
            board.set(1,1,num);
        } while (board.get(0,0) == board.get(1,1));
        for (int i = 2; i < 9; i++) {
            do {
                num = rand.nextInt(9) + 1;
                board.set(i,j,num);
            } while (board.get(i,j) == board.get(i - 1,j - 1)
                    || board.get(i,j) == board.get(i - 2,j - 2));
            j++;
        }

    }



    public boolean isSafe(SudokuBoard board, int rzad, int kolumna, int i) {
        for (int y = 0; y <= 8; y++) {
            if (board.get(rzad,y) == i) {
                return false;
            }
        }
        for (int x = 0; x <= 8; x++) {
            if (board.get(x,kolumna) == i) {
                return false;
            }
        }
        int startRow = rzad - rzad % 3;
        int startCol = kolumna - kolumna % 3;
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(k + startRow,j + startCol) == i) {
                    return false;
                }
            }
        }
        return true;
    }



}
