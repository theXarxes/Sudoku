package sudoku;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;


public class StartWindow {

    private PlayWindow play = new PlayWindow();
    private Authors authors = new Authors();
    private static SudokuBoard sudokuBoardFromFile;
    private FileSudokuBoardDao fileSudokuBoardDao;
    private ResourceBundle bundle = ResourceBundle.getBundle("language");
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> jdbcSudokuBoardDao;
    private AuthorsLenguage authorsLenguage = new AuthorsLenguage();

    @FXML
    private ComboBox levelBox;

    @FXML
    private ComboBox jezyk;

    private static String level;
    private static String jezyk2;

    public static String getLevel() {
        return level;
    }

    public static String getJezyk2() {
        return jezyk2;
    }

    @FXML
    public void level(ActionEvent actionEvent) {
            this.level = levelBox.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    public void language(ActionEvent actionEvent) {
        this.jezyk2 = jezyk.getSelectionModel().getSelectedItem().toString();

    }


    @FXML
    public void startGame(ActionEvent actionEvent) throws IOException {
            Prepare.buildStage("playWindow.fxml",bundle);
    }

    @FXML
    private void authors(ActionEvent actionEvent) {

        if (Locale.getDefault().toString().equals("pl")) {
            Pop.messageBox("Autorzy",
                    ("Autorzy: " + authorsLenguage.message(authors.getObject("1").toString(),
                            authors.getObject("2").toString())));
        } else {
            Pop.messageBox("Authors",
                    ("Authors: " + authorsLenguage.message(authors.getObject("1").toString(),
                            authors.getObject("2").toString())));
        }
    }

    @FXML
    public void load(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        fileSudokuBoardDao = new FileSudokuBoardDao("zapisOrginal");

        sudokuBoardFromFile = fileSudokuBoardDao.read();
    }

    @FXML
    public void database(ActionEvent actionEvent)
            throws IOException, ClassNotFoundException, SQLException {
        String name;
        FileChooser fileChooser = new FileChooser();
        name = fileChooser.showOpenDialog(Prepare.getStage()).getName();
        jdbcSudokuBoardDao = factory.getDatabaseDao(name);
        sudokuBoardFromFile = jdbcSudokuBoardDao.read();
    }

    public static SudokuBoard getSudokuBoardFromFile() {
        return sudokuBoardFromFile;
    }
}
