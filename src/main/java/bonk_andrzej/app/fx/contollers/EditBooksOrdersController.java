package bonk_andrzej.app.fx.contollers;

import bonk_andrzej.app.fx.modelsFx.BooksOrdersModel;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditBooksOrdersController {

    @FXML
    private ComboBox readerComboBox;
    @FXML
    private ComboBox booksComboBox;
    @FXML
    private TextField booksReturnedTextField;
    @FXML
    private DatePicker lenderDataPicker;
    @FXML
    private DatePicker returnDataPicker;
    @FXML
    private DatePicker actualReturnDate;
    @FXML
    private Button addButton;
    private BooksOrdersModel booksOrdersModel;

    @FXML
    private void initialize() {
        booksOrdersModel = new BooksOrdersModel();
        try {
            booksOrdersModel.initAllObservableList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        bindProperties();
        disableAddButton();
        DialogsUtils.setTextFieldNumericOnly(booksReturnedTextField);
    }

    @FXML
    private void editBookOrderOnAction() {
        try {
            booksOrdersModel.updateOrderInDB();
            clearFields();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    void bindProperties() {
        booksComboBox.setItems(booksOrdersModel.getBookFxObservableList());
        readerComboBox.setItems(booksOrdersModel.getReaderFxObservableList());

        booksComboBox.valueProperty().bindBidirectional(booksOrdersModel.getBookOrdersFxObjectProperty().bookFxProperty());
        readerComboBox.valueProperty().bindBidirectional(booksOrdersModel.getBookOrdersFxObjectProperty().readerFxProperty());
        booksReturnedTextField.textProperty().bindBidirectional(booksOrdersModel.getBookOrdersFxObjectProperty().amountReturnedBooksNowProperty());
        lenderDataPicker.valueProperty().bindBidirectional(booksOrdersModel.getBookOrdersFxObjectProperty().lenderDateProperty());
        actualReturnDate.valueProperty().bindBidirectional(booksOrdersModel.getBookOrdersFxObjectProperty().actualDateOfReturnProperty());
        returnDataPicker.valueProperty().bindBidirectional(booksOrdersModel.getBookOrdersFxObjectProperty().returnDateProperty());
    }

    private void disableAddButton() {
        addButton.disableProperty().bind(booksComboBox.valueProperty().isNull()
                .or(readerComboBox.valueProperty().isNull()
                        .or(booksReturnedTextField.textProperty().isEmpty())
                        .or(lenderDataPicker.valueProperty().isNull())
                ));
    }

    private void clearFields() {
        booksComboBox.getSelectionModel().clearSelection();
        readerComboBox.getSelectionModel().clearSelection();
        booksReturnedTextField.clear();
        lenderDataPicker.getEditor().clear();
        returnDataPicker.getEditor().clear();
        actualReturnDate.getEditor().clear();
    }

    public BooksOrdersModel getBooksOrdersModel() {
        return booksOrdersModel;
    }
}
