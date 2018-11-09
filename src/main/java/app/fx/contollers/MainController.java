package app.fx.contollers;

import app.utils.DialogsUtils;
import app.utils.FxmlUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Optional;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;

public class MainController {
    public static final String BORDER_PAINE_MAIN_FXML = "/fxml/BorderPaineMain.fxml";

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuVBoxController menuVBoxController;

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
        Locale.setDefault(Locale.getDefault());
        Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PAINE_MAIN_FXML);
//        Scene scene = new Scene(borderPane);
        newPrimaryStage.setScene(new Scene(borderPane));
        newPrimaryStage.show();
//        primaryStage.setScene(scene);
//        primaryStage.setTitle(FxmlUtils.setResourceBundle().getString("tittle.application"));
//        primaryStage.show();


//        main.startDefaultWindow(newPrimaryStage);


    }

    public void setLanguageToEn() {
        Stage newPrimaryStage = new Stage();
        Locale.setDefault(Locale.ENGLISH);
                Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PAINE_MAIN_FXML);
//        Scene scene = new Scene(borderPane);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle(FxmlUtils.setResourceBundle().getString("tittle.application"));
//        primaryStage.show();
        newPrimaryStage.setScene(new Scene(borderPane));
        newPrimaryStage.show();


//        main.startDefaultWindow(newPrimaryStage);


    }


}
