package bonk_andrzej.app.fx.contoller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class MenuController {
    private static final String LIST_BOOKS_FXML = "/fxml/ListBooks.fxml";
    private static final String ADD_READER_FXML = "/fxml/AddReader.fxml";
    private static final String ADD_BOOK_FXML = "/fxml/AddBook.fxml";
    private static final String ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";
    private static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";
    private static final String READER_LIST_FXML = "/fxml/OrdersList.fxml";
    private static final String RENT_BOOK_FXML = "/fxml/AddBookOrders.fxml";

    private MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    private void OpenListBooksWindow() {
        mainController.setCenter(LIST_BOOKS_FXML);
    }

    @FXML
    private void openAddReaderWindow() {
        mainController.setCenter(ADD_READER_FXML);
    }

    @FXML
    private void openAddBookWindow() {
        mainController.setCenter(ADD_BOOK_FXML);
    }


    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void openAddAuthorWindow() {
        mainController.setCenter(ADD_AUTHOR_FXML);
    }

    @FXML
    private void openAddCategoryWindow() {
        mainController.setCenter(ADD_CATEGORY_FXML);
    }

    @FXML
    private void openReaderListWindow() {
        mainController.setCenter(READER_LIST_FXML);
    }

    @FXML
    private void openRentBookWindow() {
        mainController.setCenter(RENT_BOOK_FXML);
    }
}
