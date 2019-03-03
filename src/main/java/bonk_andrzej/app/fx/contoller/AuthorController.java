package bonk_andrzej.app.fx.contoller;

import bonk_andrzej.app.fx.view.AuthorFx;
import bonk_andrzej.app.fx.modelFx.AuthorModel;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.exception.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.Optional;

public class AuthorController {


    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private Button addButton;
    @FXML
    private TableView<AuthorFx> authorTableView;
    @FXML
    private TableColumn<AuthorFx, String> nameColumn;
    @FXML
    private TableColumn<AuthorFx, String> surnameColumn;
    @FXML
    private MenuItem deleteMenuItem;
    private AuthorModel authorModel;

    @FXML
    private void initialize() {
        authorModel = new AuthorModel();
        try {
            authorModel.initAuthorObservableList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        bindProperties();
    }

    @FXML
    public void addAuthorOnAction() {
        try {
            authorModel.saveOrUpdateAuthorInDb();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        nameTextField.clear();
        surnameTextField.clear();
    }

    @FXML
    private void deleteAuthorOnAction() {
        Optional<ButtonType> result = DialogsUtils.confirmAlert("delete.author.title", "delete.author.header");
        if (result.get() == ButtonType.OK) {
            try {
                authorModel.deleteAuthorInDB();
            } catch (ApplicationException e) {
                DialogsUtils.errorDialogs(e.getMessage());
            }
        }
    }

    @FXML
    private void onEditCommitName(TableColumn.CellEditEvent<AuthorFx, String> authorFxStringCellEditEvent) {
        authorModel.getAuthorFxObjectProperty().setName(authorFxStringCellEditEvent.getNewValue());
        try {
            authorModel.saveOrUpdateAuthorInDb();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());

        }
    }

    @FXML
    public void onEditCommitSurname(TableColumn.CellEditEvent<AuthorFx, String> authorFxStringCellEditEvent) {
        authorModel.getAuthorFxObjectProperty().setSurname(authorFxStringCellEditEvent.getNewValue());
        try {
            authorModel.saveOrUpdateAuthorInDb();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    private void bindProperties(){
        bindColumns();
        bindTextFields();
    }


    private void bindColumns() {
        authorTableView.setItems(authorModel.getAuthorFxObservableList());
        nameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
        surnameColumn.setCellValueFactory(cellDate -> cellDate.getValue().surnameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        authorTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> authorModel.setAuthorFxObjectProperty(newValue));
    }

    private void bindTextFields() {
        authorModel.getAuthorFxObjectProperty().nameProperty().bind(nameTextField.textProperty());
        authorModel.getAuthorFxObjectProperty().surnameProperty().bind(surnameTextField.textProperty());
        addButton.disableProperty().bind(nameTextField.textProperty().isEmpty().or(surnameTextField.textProperty().isEmpty()));
        deleteMenuItem.disableProperty().bind(authorTableView.getSelectionModel().selectedItemProperty().isNull());
    }

}
