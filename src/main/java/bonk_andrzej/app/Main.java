package bonk_andrzej.app;

import bonk_andrzej.app.utils.FxmlUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    public static final String BORDER_PAINE_MAIN_FXML = "/fxml/MainWindow.fxml";
    public static final Locale ENGLISH_LOCALE = Locale.ENGLISH;


    public void start(Stage primaryStage) throws Exception {
//        AddObjectToDB.addObjectToDB();
        startWindow(primaryStage, ENGLISH_LOCALE);

    }

    public static void startWindow(Stage primaryStage, Locale locale ) {
        Locale.setDefault(locale);
        Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PAINE_MAIN_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("tittle.application"));
        primaryStage.show();
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }
}
