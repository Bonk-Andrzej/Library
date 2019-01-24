package bonk_andrzej.app.utils;

import javafx.scene.control.*;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {

    private DialogsUtils() {
    }

    private static ResourceBundle bundle = FxmlUtils.getResourceBundle();

    public static void dialogAboutApplication() {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("about.title"));
        informationAlert.setHeaderText(bundle.getString("about.header"));
        informationAlert.setContentText(bundle.getString("about.content"));
        informationAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmAlert(String title, String header) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(bundle.getString(title));
        confirmationDialog.setHeaderText(bundle.getString(header));
        return confirmationDialog.showAndWait();
    }

    public static void errorDialogs(String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));
        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();
    }

    public static void specificBooksOrdersError(String bundleKey) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));
        TextArea textArea = new TextArea(bundle.getString(bundleKey));
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();
    }

    public static String editDialog(String values) {
        TextInputDialog dialog = new TextInputDialog(values);
        dialog.setTitle(bundle.getString("edit.title"));
        dialog.setHeaderText(bundle.getString("edit.header"));
        dialog.setContentText(bundle.getString("edit.content"));
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    public static void setTextFieldNumericOnly(TextField textFieldName) {
        textFieldName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textFieldName.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

}
