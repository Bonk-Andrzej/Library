package bonk_andrzej.app.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {


    static ResourceBundle bundle = FxmlUtils.getResourceBundle();

    public static void dialogAboutApplication() {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("about.title"));
        informationAlert.setHeaderText(bundle.getString("about.header"));
        informationAlert.setContentText(bundle.getString("about.content"));
        informationAlert.showAndWait();
    }


    public static Optional<ButtonType> confirmAlert() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(bundle.getString("exit.title"));
        confirmationDialog.setHeaderText(bundle.getString("exit.header"));
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }

    public static void errorDialogs(String error) {
        Alert errorAllert = new Alert(Alert.AlertType.ERROR);
        errorAllert.setTitle(bundle.getString("error.title"));
        errorAllert.setHeaderText(bundle.getString("error.header"));
        TextArea textArea = new TextArea(error);
        errorAllert.getDialogPane().setContent(textArea);
        errorAllert.showAndWait();
    }
    public static void specificBooksOrdersError(String bundleKey){
        Alert errorAllert = new Alert(Alert.AlertType.ERROR);
        errorAllert.setTitle(bundle.getString("error.title"));
        errorAllert.setHeaderText(bundle.getString("error.header"));
        TextArea textArea = new TextArea(bundle.getString(bundleKey));
        errorAllert.getDialogPane().setContent(textArea);
        errorAllert.showAndWait();
    }
    public static void someBooksArentReturnedError(){
        Alert errorAllert = new Alert(Alert.AlertType.ERROR);
        errorAllert.setTitle(bundle.getString("error.title"));
        errorAllert.setHeaderText(bundle.getString("error.header"));
        TextArea textArea = new TextArea(bundle.getString("error.number.books.returned"));
        errorAllert.getDialogPane().setContent(textArea);
        errorAllert.showAndWait();
    }

    public static String editDialog (String values){
        TextInputDialog dialog = new TextInputDialog(values);
        dialog.setTitle(bundle.getString("edit.title"));
        dialog.setHeaderText(bundle.getString("edit.header"));
        dialog.setContentText(bundle.getString("edit.content"));
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        else return null;
    }
    public static void setTextFieldNumericOnly(TextField textFieldName) {
        textFieldName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textFieldName.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

}
