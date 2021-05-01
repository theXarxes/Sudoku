package sudoku;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SudokuField implements Serializable,Comparable<SudokuField>,Cloneable {
    private static Logger logger = LoggerFactory.getLogger(SudokuField.class);

    private int value;

    public SudokuField() {
        logger.info("Stowrzono {}", SudokuField.class);
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        if (value < 0 || value > 9) {
            throw new BadValueException("Zle numerki");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
               .toString();
    }

    @Override
    public int compareTo(SudokuField o) {
        int result = Integer.compare(o.getFieldValue(),this.getFieldValue());
        return result;
    }

    @Override
    public SudokuField clone() {
        try {

            return (SudokuField) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error(this.getClass().getName());
            return null;
        }
    }
}
