package sudoku;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {

    private Prepare prepare;
    private ResourceBundle resources  = ResourceBundle.getBundle("Language");

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
       prepare.buildStage(stage, "languageWindow.fxml", "Language",resources);

    }
}
