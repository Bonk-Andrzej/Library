package bonk_andrzej.app.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Locale;
import java.util.ResourceBundle;


public class FxmlUtils {
    private FxmlUtils() {
    }

    public static Pane fxmlLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        loader.setResources(getResourceBundle());
        try {
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            DialogsUtils.errorDialogs(e.getMessage());
        }
        return null;
    }

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("bundles.messages");
    }

    public static ResourceBundle getBundleForApplicationErrors() {
        Locale.setDefault(new Locale("eng"));
        return ResourceBundle.getBundle("bundles.messages");
    }

    public static FXMLLoader getLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        loader.setResources(getResourceBundle());
        return loader;
    }

    public static Button createButton(Class classes, String path) {
        Button button = new Button();
        Image image = new Image(classes.getResource(path).toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }
}
