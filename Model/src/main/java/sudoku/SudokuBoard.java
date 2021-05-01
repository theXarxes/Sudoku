package sudoku;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuBoard implements Observable, Serializable, Cloneable {
    private List<List<SudokuField>> board;
    private final SudokuSolver sudokuSolver;
    private Set<sudoku.Observer> observerset = new HashSet<>();
    private int newestMove;
    private static Logger logger = LoggerFactory.getLogger(SudokuBoard.class);
    private String lg;


    public SudokuBoard(SimpleSudokuSolver solver) {
        this.board = Arrays.asList(new List[9]);
        for (int i = 0;i < 9;i++) {
            board.set(i, Arrays.asList(new SudokuField[9]));
        }

        for (int i = 0;i < 9; i++) {
            for (int j = 0;j < 9;j++) {
                this.board.get(i).set(j,new SudokuField());
            }
        }
        this.sudokuSolver = solver;
        if (board.size() > 82) {
            logger.error("Blad {}",SudokuBoard.class);
            throw new BadSizeException(LanguageHelper.fabric(3));
        }
        logger.info("Stowrzono {}", SudokuBoard.class);
    }

    public boolean drawBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(get(i, j) + " ");

            }
            System.out.print(System.lineSeparator());
        }
        System.out.println(System.lineSeparator());
        return true;
    }

    public int get(int x, int y) {
        return this.board.get(x).get(y).getFieldValue();
    }

    public void clearBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.get(i).get(j).setFieldValue(0);
            }
        }
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public void set(int x, int y, int value) {
        this.board.get(x).get(y).setFieldValue(value);
        newestMove = get(x,y);
        notifyObserver();
    }

    public SudokuRow getRow(int row) {

        SudokuRow srow = new SudokuRow();
        for (int i = 0;i < 9; i++) {
            srow.setVerifyBoard(i,board.get(row).get(i).getFieldValue());
        }
        return srow;
    }

    public SudokuColumn getColumn(int column) {

        SudokuColumn scol = new SudokuColumn();
        for (int i = 0;i < 9; i++) {
            scol.setVerifyBoard(i,board.get(i).get(column).getFieldValue());
        }
        return scol;
    }

    public SudokuBox getBox(int row,int column) {
        int startRow = row - row % 3;
        int startCol = column - column % 3;
        int tmp = 0;
        SudokuBox sbox = new SudokuBox();
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                sbox.setVerifyBoard(tmp,board.get(k + startRow).get(j + startCol).getFieldValue());
                tmp++;
            }
        }
        return sbox;
    }


    public boolean checkBoard() {
        int boxX = 1;
        int boxY = 1;
        for (int i = 0; i < 9;i++) {
            if (!this.getRow(i).verify()) {
                return false;
            }
            if (!this.getColumn(i).verify()) {
                return false;
            }
            if (!this.getBox(boxX,boxY).verify()) {
                return false;
            }
            boxY = boxY + 2;
            if (boxY == 9) {
                boxY = 1;
                boxX = boxX + 2;
            }
        }
        return true;
    }

    public boolean uselessFunction() {
        return checkBoard();
    }

    public Set<Observer> getObserverset() {
        return observerset;
    }

    public SudokuBoard betterClone() {
        SimpleSudokuSolver s = new SimpleSudokuSolver();
        SudokuBoard b = new SudokuBoard(s);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                b.set(i,j,this.get(i,j));
            }
        }
        return b;
    }

    @Override
    public void attach(sudoku.Observer observer) {
        observerset.add(observer);
    }

    @Override
    public void dettach(sudoku.Observer observer) {
        observerset.remove(observer);
    }

    @Override
    public void notifyObserver() {
        observerset.forEach(Observer::update);
    }

    public int getNewestAction() {
        return newestMove;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("board", board)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equal(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board);
    }

    public String glupoty() {
        StringBuilder text = new StringBuilder();
        int[][] test = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                test[i][j] = get(i,j);
            }
        }
        for (int j = 0;j < 9;j++) {
            for (int i = 0; i < 9; i++) {
                if (i == 0) {
                    text.append("| ");
                } else if (test[i][j] == 0) {
                    text.append("   | ");
                } else {
                    text.append(Integer.toString(test[i][j]));
                    text.append(" | ");
                }
                if (i == 8) {
                    text.append("\n");
                }

            }
        }
        return text.toString();
    }

    @Override
    protected SudokuBoard clone() {
        try {
            return (SudokuBoard) super.clone();

        } catch (CloneNotSupportedException e) {
            System.out.println(this.getClass().getName());
            return null;
        }
    }

    public String toString1() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(String.valueOf(get(i, j)));
            }
        }

        return builder.toString();
    }

    public SudokuBoard createSudokuBoard(String data) {
        SimpleSudokuSolver solver = null;
        SudokuBoard sudoku = new SudokuBoard(null);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                set(i, j, Character.getNumericValue(data.charAt(i * 9 + j)));
            }
        }
        return sudoku;
    }



}