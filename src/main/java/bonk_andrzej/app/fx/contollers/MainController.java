package bonk_andrzej.app.fx.contollers;

import bonk_andrzej.app.Main;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.FxmlUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Optional;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;

public class MainController {
    public static final Locale DEFAULT_LOCALE = Locale.getDefault();
    public static final Locale ENGLISH_LOCALE = Locale.ENGLISH;

    @FXML
    private BorderPane borderPane;

    @FXML
    MenuVBoxController menuVBoxController;

    @FXML
    private void initialize() {
        menuVBoxController.setMainController(this);
    }

    public void setCenter(String fxmlPath) {
        borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }

    public void closeApplication() {
        Optional<ButtonType> result = DialogsUtils.confirmAlert();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void setCaspian() {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }

    public void setModena() {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
    }

    public void setAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);
    }

    public void about() {
        DialogsUtils.dialogAboutApplication();
    }

    public void setLanguageToPl() {
        Stage newPrimaryStage = new Stage();
        Main.startWindow(newPrimaryStage, ENGLISH_LOCALE);
    }

    public void setLanguageToEn() {
        Stage newPrimaryStage = new Stage();
        Main.startWindow(newPrimaryStage, DEFAULT_LOCALE);
    }
}
