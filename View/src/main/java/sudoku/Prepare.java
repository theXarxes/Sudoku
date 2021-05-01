package sudoku;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Prepare {
    private static Stage stage;

    private static void setStage(Stage stage) {
        Prepare.stage = stage;
    }

    private static Parent loadFxml(String fxml, ResourceBundle bundle) throws IOException {
        return new FXMLLoader(Prepare.class.getResource(fxml), bundle).load();
    }

    public static void buildStage(String filePath, ResourceBundle bundle) throws IOException {
        stage.setScene(new Scene(loadFxml(filePath, bundle)));
        stage.sizeToScene();
        stage.show();
    }

    public static void buildStage(Stage stage, String filePath, String title, ResourceBundle bundle)
            throws IOException {
        setStage(stage);
        stage.setScene(new Scene(loadFxml(filePath, bundle)));
        stage.setTitle(title);
        stage.sizeToScene();
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }


}
