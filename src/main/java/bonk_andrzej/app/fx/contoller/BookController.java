package bonk_andrzej.app.fx.contoller;

import bonk_andrzej.app.fx.view.AuthorFx;
import bonk_andrzej.app.fx.modelFx.BookModel;
import bonk_andrzej.app.fx.view.CategoryFx;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.exception.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class BookController {

    @FXML
    private Button addButton;
    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    @FXML
    private ComboBox<AuthorFx> authorComboBox;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextArea descTextArea;
    @FXML
    private Slider ratingSlider;
    @FXML
    private TextField isbnTextField;
    @FXML
    private DatePicker releaseDatePicker;
    @FXML
    private TextField amountBooksTextField;
    private BookModel bookModel;


    @FXML
    private void initialize() {
        bookModel = new BookModel();
        try {
            bookModel.initAllObservableList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        bindProperties();
        DialogsUtils.setTextFieldNumericOnly(amountBooksTextField);
        disableAddButtom();
    }

    @FXML
    private void addBookOnAction() {
        try {
            bookModel.saveBookInDB();
            clearAllFields();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    @FXML
    void bindProperties() {
        bindComboBoxes();
        bindTextFields();
    }

    private void bindTextFields() {
        titleTextField.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().titleProperty());
        descTextArea.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().descriptionProperty());
        ratingSlider.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().ratingProperty());
        isbnTextField.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().isbnProperty());
        releaseDatePicker.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().releaseDateProperty());
        amountBooksTextField.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().leftBooksForRentProperty());
    }

    private void bindComboBoxes() {
        categoryComboBox.setItems(bookModel.getCategoryFxObservableList());
        authorComboBox.setItems(bookModel.getAuthorFxObservableList());
        authorComboBox.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().authorFxProperty());
        categoryComboBox.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().categoryFxProperty());
    }

    private void disableAddButtom() {
        addButton.disableProperty().bind(categoryComboBox.valueProperty().isNull()
                .or(authorComboBox.valueProperty().isNull())
                .or(titleTextField.textProperty().isEmpty()
                        .or(descTextArea.textProperty().isEmpty())
                        .or(ratingSlider.valueProperty().isEqualTo(0))
                        .or(isbnTextField.textProperty().isEmpty())
                        .or(releaseDatePicker.valueProperty().isNull())
                        .or(amountBooksTextField.textProperty().isEmpty())

                ));
    }

    private void clearAllFields() {
        authorComboBox.getSelectionModel().clearSelection();
        categoryComboBox.getSelectionModel().clearSelection();
        titleTextField.clear();
        descTextArea.clear();
        ratingSlider.setValue(1);
        isbnTextField.clear();
        releaseDatePicker.getEditor().clear();
        amountBooksTextField.clear();
    }

    BookModel getBookModel() {
        return bookModel;
    }
}
