package bonk_andrzej.app.fx.contollers;

import bonk_andrzej.app.fx.modelsFx.OrderModel;
import bonk_andrzej.app.fx.modelsFx.OrdersListModel;
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
        DialogsUtils.setTextFieldNumericOnly(booksReturnedTextField);
    }

    @FXML
    private void editBookOrderOnAction() {
        try {
            orderModel.updateOrderInDB();
            clearFields();

        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    void bindProperties() {
        booksComboBox.setItems(orderModel.getBookFxObservableList());
        readerComboBox.setItems(orderModel.getReaderFxObservableList());

        booksComboBox.valueProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().bookFxProperty());
        readerComboBox.valueProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().readerFxProperty());
        booksReturnedTextField.textProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().amountReturnedBooksNowProperty());
        lenderDataPicker.valueProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().lenderDateProperty());
        actualReturnDate.valueProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().actualDateOfReturnProperty());
        returnDataPicker.valueProperty().bindBidirectional(orderModel.getBookOrdersFxObjectProperty().returnDateProperty());
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

    OrderModel getOrderModel() {
        return orderModel;
    }


}
