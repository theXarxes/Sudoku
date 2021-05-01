package sudoku;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class Authors extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{{"1","Patryk Wyrwich",},{"2","Szymon Depcik"}};
    }
}
