package bonk_andrzej.app;

import bonk_andrzej.app.db.dao.GenericCrud;
import bonk_andrzej.app.utils.FxmlUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    private static final String BORDER_PAINE_MAIN_FXML = "/fxml/MainWindow.fxml";
    private static final String ENGLISH_LOCALE = "eng";


    public void start(Stage primaryStage) {
//        AddObjectToDB.addObjectToDB();
        startWindow(primaryStage, ENGLISH_LOCALE);
        GenericCrud genericCrud = new GenericCrud();
    }

    public static void startWindow(Stage primaryStage, String locale) {
        Locale.setDefault(new Locale(locale));
        Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PAINE_MAIN_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("tittle.application"));
        primaryStage.show();
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }
}
