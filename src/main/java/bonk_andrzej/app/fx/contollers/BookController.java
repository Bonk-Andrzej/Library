package bonk_andrzej.app.fx.contollers;

import bonk_andrzej.app.fx.view.AuthorFx;
import bonk_andrzej.app.fx.modelsFx.BookModel;
import bonk_andrzej.app.fx.view.CategoryFx;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import lombok.Getter;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.function.UnaryOperator;


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

    protected void bindProperties() {
        categoryComboBox.setItems(bookModel.getCategoryFxObservableList());
        authorComboBox.setItems(bookModel.getAuthorFxObservableList());

        authorComboBox.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().authorFxProperty());
        categoryComboBox.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().categoryFxProperty());
        titleTextField.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().titleProperty());
        descTextArea.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().descriptionProperty());
        ratingSlider.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().ratingProperty());
        isbnTextField.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().isbnProperty());
        releaseDatePicker.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().releaseDateProperty());
        amountBooksTextField.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().amounrtProperty());


    }

    private void setAmountBooksTextFieldNumericOnly() {
        amountBooksTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    amountBooksTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
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

    public BookModel getBookModel() {
        return bookModel;
    }
}
