package sudoku;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;


public class LanguageWindow {
    private Prepare prepare;
    private static String jezyk1;
    private HashMap<String, String> dictionary = new HashMap<String, String>();
    private LanguageHelper languageHelper = new LanguageHelper();
    private LanguageHelper2 languageHelper2 = new LanguageHelper2();
    @FXML
    private ComboBox jezyk;

    @FXML
    private void initialize()  {
        this.dictionary.put("Polski","pl");
        this.dictionary.put("Angielski","en");
    }

    @FXML
    public void language(ActionEvent actionEvent) {
        this.jezyk1 = jezyk.getSelectionModel().getSelectedItem().toString();
        languageHelper.setLang(jezyk1);
        languageHelper2.setLang(jezyk1);
    }

    @FXML
    public void apply(ActionEvent actionEvent) throws LanguageException  {
        try {
            Locale.setDefault(new Locale(this.dictionary.get(jezyk1)));
            ResourceBundle resources  = ResourceBundle.getBundle("Language");
            prepare.buildStage("StartWindow.fxml", resources);
        } catch (IOException e) {
            throw new LanguageException(LanguageHelper2.fabric(1));

        }
    }
}
