package app.fx.contollers;

import app.fx.view.AuthorFx;
import app.fx.modelsFx.BookModel;
import app.fx.view.CategoryFx;
import app.utils.DialogsUtils;
import app.utils.exceptions.ApplicationException;
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

    private BookModel bookModel = new BookModel();


    @FXML
    public void initialize() {
        bookModel = new BookModel();
        try {
            bookModel.initObservableCategoryListAndAuthorList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        bindProperties();
        setAmountBooksTextFieldNumericOnly();
        disableAddButtom();
    }

    @FXML
    public void addBookOnAction() {
        try {
            bookModel.saveBookInDB();
            clearAllFields();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    private void bindProperties() {
        categoryComboBox.setItems(bookModel.getCategoryFxObservableList());
        authorComboBox.setItems(bookModel.getAuthorFxObservableList());
        bookModel.getBookFxObjectProperty().categoryFxObjectPropertyProperty().bind(categoryComboBox.valueProperty());
        bookModel.getBookFxObjectProperty().authorFxObjectPropertyProperty().bind(authorComboBox.valueProperty());
        bookModel.getBookFxObjectProperty().titleProperty().bind(titleTextField.textProperty());
        bookModel.getBookFxObjectProperty().descriptionProperty().bind(descTextArea.textProperty());
        bookModel.getBookFxObjectProperty().ratingProperty().bind(ratingSlider.valueProperty());
        bookModel.getBookFxObjectProperty().isbnProperty().bind(isbnTextField.textProperty());
        bookModel.getBookFxObjectProperty().releaseDateProperty().bind(releaseDatePicker.valueProperty());
        bookModel.getBookFxObjectProperty().amounrtProperty().bind(amountBooksTextField.textProperty());
    }

    private void setAmountBooksTextFieldNumericOnly() {
        bookModel.getBookFxObjectProperty().amounrtProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                amountBooksTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
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

}
