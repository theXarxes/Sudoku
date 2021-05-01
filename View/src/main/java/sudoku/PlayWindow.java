package sudoku;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.StringUtils;

public class PlayWindow  {


    private Pop popOutWindow = new Pop();
    private SudokuBoard sudokuBoardCopy = new SudokuBoard(null);
    private SudokuBoard sudokuBoardCopyCheck = new SudokuBoard(null);
    private SimpleSudokuSolver solver = new SimpleSudokuSolver();
    private Difficulty difficultyLevel = new Difficulty();
    private SudokuBoard sudokuBoard = new SudokuBoard(solver);
    private TextField textField = new TextField();
    private TextField[] textFieldTab = new TextField[81];
    private FileChooser fileChooser;
    private File file;
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    @FXML
    private GridPane sudokuBoardGrid;


    @FXML
    private void initialize() {
        if (StartWindow.getSudokuBoardFromFile() != null) {
            sudokuBoard = StartWindow.getSudokuBoardFromFile();
            sudokuBoardCopy = sudokuBoard.clone();
        } else {
            solver.solve(sudokuBoard);
            sudokuBoardCopyCheck = sudokuBoard.betterClone();
            difficultyLevel.choose(sudokuBoard, StartWindow.getLevel());
            sudokuBoardCopy = sudokuBoard.betterClone();
            sudokuBoard.drawBoard();
        }
        fillGrid();
    }


    @FXML
    public void check(ActionEvent actionEvent) {
        try {
            corrector();
            if (check()) {
                Pop.messageBox("","You won");
            } else {
                Pop.messageBox("","You  lost");
            }
        } catch (NoMatchingArgumentException e) {
            Pop.messageBox("","Cyferek nie umiesz wpisywac czy co?");
        }
    }

    public boolean check() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String fieldValue = ((TextField) sudokuBoardGrid
                        .getChildren().get(i * 9 + j)).getText();
                if (!String.valueOf(sudokuBoardCopyCheck.get(i, j)).equals(fieldValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    @FXML
    public void save(ActionEvent actionEvent) throws IOException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!StringUtils.isNumeric(((TextField)sudokuBoardGrid.getChildren()
                        .get(i * 9 + j)).getText())) {
                    sudokuBoard.set(i,j,0);
                } else {
                    sudokuBoard.set(i,j,Integer.parseInt(((TextField)
                            sudokuBoardGrid.getChildren().get(i * 9 + j)).getText()));
                }
            }
        }
        FileSudokuBoardDao f1 = new FileSudokuBoardDao("zapisEdit");
        FileSudokuBoardDao f2 = new FileSudokuBoardDao("zapisOrginal");
        FileSudokuBoardDao f3 = new FileSudokuBoardDao("zapisOrginalSolved");
        f2.write(sudokuBoardCopy);
        f1.write(sudokuBoard);
        f3.write(sudokuBoardCopyCheck);
    }

    private void fillGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMinSize(30, 30);
                textField.setFont(Font.font(12));
                if (!(sudokuBoard.get(i, j) == 0)) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                } else  {
                    textField.setBackground(new
                            Background(new BackgroundFill(Color.PALEGREEN,new CornerRadii(4),
                            new Insets(2))));
                    textField.setText("");
                }
                this.textFieldTab[i * 9 + j] = textField;
                sudokuBoardGrid.add(this.textFieldTab[i * 9 + j], j, i);
            }
        }
    }


    public void corrector() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!StringUtils.isNumeric(((TextField)
                        sudokuBoardGrid.getChildren().get(i * 9 + j)).getText())) {
                    throw new BadValueException(LanguageHelper.fabric(1));
                }
                Integer fieldValue = Integer.valueOf(((TextField) sudokuBoardGrid
                        .getChildren().get(i * 9 + j)).getText());
                if (fieldValue > 9 || fieldValue < 1) {
                    throw new BadValueException(LanguageHelper.fabric(1));
                }
            }
        }
    }

    public PlayWindow(){
    }

    @FXML
    public void database(ActionEvent actionEvent)
            throws IOException, SQLException, ClassNotFoundException {
        fileChooser = new FileChooser();
        file = fileChooser.showSaveDialog(Prepare.getStage());
        fileSudokuBoardDao = factory.getDatabaseDao(file.getName());
        fileSudokuBoardDao.write(sudokuBoard);
    }

}









