package bonk_andrzej.app.fx.contollers;

import bonk_andrzej.app.fx.modelsFx.ReaderModel;
import bonk_andrzej.app.fx.view.ReaderFx;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.Optional;

public class ReaderController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private Button addButton;
    @FXML
    private TableView<ReaderFx> readerTableView;
    @FXML
    private TableColumn<ReaderFx, String> nameColumn;
    @FXML
    private TableColumn<ReaderFx, String> surnameColumn;
    @FXML
    private MenuItem deleteMenuItem;
    private ReaderModel readerModel;

    @FXML
    private void initialize() {
        readerModel = new ReaderModel();
        try {
            readerModel.initAllObservableList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        textFieldBindings();
        tableViewBindings();
    }

    @FXML
    private void addReaderOnAction() {
        try {
            readerModel.saveOrUpdateReaderToDB();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        nameTextField.clear();
        surnameTextField.clear();
    }

    @FXML
    private void deleteReaderOnAction() {
        Optional<ButtonType> result = DialogsUtils.confirmAlert("delete.reader.title", "delete.reader.header");
        if (result.get() == ButtonType.OK) {
            try {
                readerModel.deleteAuthorInDB();
            } catch (ApplicationException e) {
                DialogsUtils.errorDialogs(e.getMessage());
            }
        }
    }

    @FXML
    private void onEditCommitName(TableColumn.CellEditEvent<ReaderFx, String> readerFxStringCellEditEvent) {
        readerModel.getReaderFxObjectProperty().setName(readerFxStringCellEditEvent.getNewValue());
        try {
            readerModel.saveOrUpdateReaderToDB();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    @FXML
    private void onEditCommitSurname(TableColumn.CellEditEvent<ReaderFx, String> readerFxStringCellEditEvent) {
        readerModel.getReaderFxObjectProperty().setSurname(readerFxStringCellEditEvent.getNewValue());
        try {
            readerModel.saveOrUpdateReaderToDB();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    private void textFieldBindings() {
        readerModel.getReaderFxObjectProperty().nameProperty().bind(nameTextField.textProperty());
        readerModel.getReaderFxObjectProperty().surnameProperty().bind(surnameTextField.textProperty());
        addButton.disableProperty().bind(nameTextField.textProperty().isEmpty().or(surnameTextField.textProperty().isEmpty()));
        deleteMenuItem.disableProperty().bind(readerTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    private void tableViewBindings() {
        readerTableView.setItems(readerModel.getReaderFxObservableList());
        nameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
        surnameColumn.setCellValueFactory(cellDate -> cellDate.getValue().surnameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        readerTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> readerModel.setReaderFxObjectProperty(newValue));
    }

}
