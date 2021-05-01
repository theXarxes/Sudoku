package sudoku;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public abstract class SudokuVerify implements Serializable,Cloneable {
    private static Logger logger = LoggerFactory.getLogger(SudokuVerify.class);
    private List<SudokuField> verifyBoard;

    public SudokuVerify() {
        this.verifyBoard = new ArrayList<>();
        for (int i = 0;i < 9;i++) {
            verifyBoard.add(i,new SudokuField());
        }
        if (verifyBoard.size() > 10) {
            throw new BadSizeException(LanguageHelper.fabric(3));
        }
        logger.info("Stowrzono {}", SudokuVerify.class);
    }

    public boolean verify() {
        for (int y = 0; y < 9; y++) {
            for (int j = 0;j < 9;j++) {
                if (verifyBoard.get(y).getFieldValue()
                        == verifyBoard.get(j).getFieldValue() && y != j) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setVerifyBoard(int i, int value) {
        this.verifyBoard.get(i).setFieldValue(value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("verifyBoard", verifyBoard)
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
        SudokuVerify that = (SudokuVerify) o;
        return Objects.equal(verifyBoard, that.verifyBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(verifyBoard);
    }

    @Override
    protected SudokuVerify clone() {
        try {
            return (SudokuVerify) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error(this.getClass().getName());
            return null;
        }
    }
}
