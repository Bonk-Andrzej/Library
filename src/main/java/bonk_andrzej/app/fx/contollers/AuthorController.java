package bonk_andrzej.app.fx.contollers;

import bonk_andrzej.app.fx.view.AuthorFx;
import bonk_andrzej.app.fx.modelsFx.AuthorModel;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

public class AuthorController {

    private AuthorModel authorModel;
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

    @FXML
    public void addAuthorOnAction() {
        try {
            authorModel.saveAuthorInDb();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        nameTextField.clear();
        surnameTextField.clear();
    }

    @FXML
    public void deleteAuthorOnAction() {
        try {
            authorModel.deleteAuthorInDB();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    @FXML
    public void onEditCommitName(TableColumn.CellEditEvent<AuthorFx, String> authorFxStringCellEditEvent) {
        authorModel.getAuthorFxObjectProperty().setName(authorFxStringCellEditEvent.getNewValue());
        try {
            authorModel.updateAuthorInDb();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());

        }
    }

    @FXML
    public void onEditCommitSurname(TableColumn.CellEditEvent<AuthorFx, String> authorFxStringCellEditEvent) {
        authorModel.getAuthorFxObjectProperty().setSurname(authorFxStringCellEditEvent.getNewValue());
        try {
            authorModel.updateAuthorInDb();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    public void initialize() {
        authorModel = new AuthorModel();
        try {
            authorModel.initializeAuthorFromDb();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        textFieldBindings();
        tableViewBindings();
    }

    private void tableViewBindings() {
        authorTableView.setItems(authorModel.getAuthorFxObservableList());
        nameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
        surnameColumn.setCellValueFactory(cellDate -> cellDate.getValue().surnameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        authorTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> authorModel.setAuthorFxObjectProperty(newValue));
    }

    private void textFieldBindings() {
        authorModel.authorFxObjectPropertyProperty().get().nameProperty().bind(nameTextField.textProperty());
        authorModel.authorFxObjectPropertyProperty().get().surnameProperty().bind(surnameTextField.textProperty());
        addButton.disableProperty().bind(nameTextField.textProperty().isEmpty().or(surnameTextField.textProperty().isEmpty()));
        deleteMenuItem.disableProperty().bind(authorTableView.getSelectionModel().selectedItemProperty().isNull());
    }


}
