package app.contollers;

import app.dialogs.DialogsUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;

public class MainController {


    @FXML
    private BorderPane borderPane;
    @FXML
    private TopMenuButtonsController upperButtomHBoxController;//musze pamietac by dac taka sama nazwee + controller z id w fxml !!!!!!

    @FXML
    private void initialize() {
        upperButtomHBoxController.setMainController(this);
    }// TODO DLACZEGO NIE PRZEKAZUJE REFERENCJI I WART JEST NULL

    public void setCenter(String fxmlPath) {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        borderPane.setCenter(parent);
    }

    public void closeApplication(ActionEvent actionEvent) {
        Optional<ButtonType> result = DialogsUtils.confirmAlert();
        if (result.get()== ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }

    }

    public void setCaspian(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }

    public void setModena(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
    }

    public void setAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);


    }

    public void about() {
        DialogsUtils.alertAboutApplication();

    }
}
