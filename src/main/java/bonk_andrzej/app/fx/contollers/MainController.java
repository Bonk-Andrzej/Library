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

import java.util.Optional;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;

public class MainController {
    private static final String POLISH_LOCALE = "pl";
    private static final String ENGLISH_LOCALE = "eng";

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuController menuController;


    @FXML
    private void initialize() {
        menuController.setMainController(this);
    }

    public void setCenter(String fxmlPath) {
        borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }

    @FXML
    private void closeApplication() {
        Optional<ButtonType> result = DialogsUtils.confirmAlert("exit.title", "exit.header");
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    @FXML
    private void setStylesheetCaspian() {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }

    @FXML
    private void setStylesheetModena() {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
    }

    @FXML
    private void setAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);
    }

    @FXML
    private void about() {
        DialogsUtils.dialogAboutApplication();
    }

    @FXML
    private void setLanguageToPl() {
        Stage newPrimaryStage = new Stage();
        Main.startWindow(newPrimaryStage, POLISH_LOCALE);
    }

    @FXML
    private void setLanguageToEn() {
        Stage newPrimaryStage = new Stage();
        Main.startWindow(newPrimaryStage, ENGLISH_LOCALE);
    }
}
