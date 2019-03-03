package bonk_andrzej.app.fx.contoller;

import bonk_andrzej.app.fx.modelFx.OrderModel;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.exception.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BooksOrderController {


    @FXML
    private ComboBox readerComboBox;
    @FXML
    private ComboBox booksComboBox;
    @FXML
    private TextField booksRentTextField;
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

    private OrderModel orderModel;

    @FXML
    private void initialize() {
        orderModel = new OrderModel();
        try {
            orderModel.initAllObservableList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        bindProperties();
        disableAddButton();
        DialogsUtils.setTextFieldNumericOnly(booksRentTextField);
        DialogsUtils.setTextFieldNumericOnly(booksReturnedTextField);
    }

    @FXML
    private void addReaderOnAction() {
        try {
            orderModel.saveOrderToDB();
            clearFields();
            orderModel.initAllObservableList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    @FXML
    private void bindProperties() {
        bindComboBoxes();
        bindTextFields();
    }

    private void bindTextFields() {
        booksRentTextField.textProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().amountAllBorrowedBooksProperty());
        booksReturnedTextField.textProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().amountReturnedBooksNowProperty());
        lenderDataPicker.valueProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().lenderDateProperty());
        actualReturnDate.valueProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().actualDateOfReturnProperty());
        returnDataPicker.valueProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().returnDateProperty());
    }

    private void bindComboBoxes() {
        booksComboBox.setItems(orderModel.getBookFxObservableList());
        readerComboBox.setItems(orderModel.getReaderFxObservableList());
        booksComboBox.valueProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().bookFxProperty());
        readerComboBox.valueProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().readerFxProperty());
    }

    private void disableAddButton() {
        addButton.disableProperty().bind(booksComboBox.valueProperty().isNull()
                .or(readerComboBox.valueProperty().isNull()
                        .or(booksRentTextField.textProperty().isEmpty())
                        .or(booksReturnedTextField.textProperty().isEmpty())
                        .or(lenderDataPicker.valueProperty().isNull())
                ));
    }

    private void clearFields() {
        booksComboBox.getSelectionModel().clearSelection();
        readerComboBox.getSelectionModel().clearSelection();
        booksRentTextField.clear();
        booksReturnedTextField.clear();
        lenderDataPicker.getEditor().clear();
        returnDataPicker.getEditor().clear();
        actualReturnDate.getEditor().clear();
    }
}
