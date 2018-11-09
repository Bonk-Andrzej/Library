package bonk_andrzej.app;

import bonk_andrzej.app.utils.AddObjectToDB;
import bonk_andrzej.app.utils.FxmlUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    public static final String BORDER_PAINE_MAIN_FXML = "/fxml/BorderPaineMain.fxml";
   private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void start(Stage primaryStage) throws Exception {
//        AddObjectToDB.addObjectToDB();
        startDefaultWindow(primaryStage);

    }

    public void startDefaultWindow(Stage primaryStage) {
        Locale.setDefault(Locale.ENGLISH);

        Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PAINE_MAIN_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("tittle.application"));
        primaryStage.show();
        setPrimaryStage(primaryStage);
    }
}
