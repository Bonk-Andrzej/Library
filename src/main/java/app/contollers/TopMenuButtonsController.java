package app.contollers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {


    public static final String FXML_LIBARY_FXML = "/fxml/Libary.fxml";
    public static final String FXML_LIST_BOOKS_FXML = "/fxml/ListBooks.fxml";
    public static final String FXML_STATISTICS_FXML = "/fxml/Statistics.fxml";
    public static final String FXML_ADD_BOOK_FXML = "/fxml/AddBook.fxml";

    public MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    public void openLibrary() {
        mainController.setCenter(FXML_LIBARY_FXML);
    }

    @FXML
    public void OpenListBooks() {
        mainController.setCenter(FXML_LIST_BOOKS_FXML);
    }

    @FXML
    public void openStatistics() {
        mainController.setCenter(FXML_STATISTICS_FXML);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void addBook() {
        if (toggleButtons.getSelectedToggle() != null) {
            toggleButtons.getSelectedToggle().setSelected(false);
        }
        mainController.setCenter(FXML_ADD_BOOK_FXML);

    }
}
