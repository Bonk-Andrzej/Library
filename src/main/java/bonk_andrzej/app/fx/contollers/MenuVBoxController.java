package bonk_andrzej.app.fx.contollers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class MenuVBoxController {
    public static final String LIST_BOOKS_FXML = "/fxml/ListBooks.fxml";
    public static final String BOOK_LENDER_FXML = "/fxml/BooksLender.fxml";
    public static final String ADD_BOOK_FXML = "/fxml/AddBook.fxml";
    public static final String ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";
    public static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";

    public MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    public void OpenListBooks() {
        mainController.setCenter(LIST_BOOKS_FXML);
    }

    @FXML
    public void openStatistics() {
        mainController.setCenter(BOOK_LENDER_FXML);
    }

    @FXML
    public void addBook() {
        mainController.setCenter(ADD_BOOK_FXML);
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    public void addAuthor() {
        mainController.setCenter(ADD_AUTHOR_FXML);
    }

    public void addCategory() {
        mainController.setCenter(ADD_CATEGORY_FXML);
    }
}
