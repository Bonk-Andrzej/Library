package app.fx.contollers;

import app.fx.view.AuthorFx;
import app.fx.view.BookFx;
import app.fx.view.CategoryFx;
import app.fx.modelsFx.ListBooksModel;
import app.utils.DialogsUtils;
import app.utils.exceptions.ApplicationException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class ListBooksController {


    @FXML
    private ComboBox categoryComboBox;
    @FXML
    private ComboBox authorComboBox;
    @FXML
    private TableView<BookFx> booksTableView;
    @FXML
    private TableColumn<BookFx, String> titleColumn;
    @FXML
    private TableColumn<BookFx, String> descColumn;
    @FXML
    private TableColumn<BookFx, AuthorFx> authorColumn;
    @FXML
    private TableColumn<BookFx, CategoryFx> categoryColumn;
    @FXML
    private TableColumn<BookFx, Number> ratingColumn;
    @FXML
    private TableColumn<BookFx, String> isbnColumn;
    @FXML
    private TableColumn<BookFx, LocalDate> releaseColumn;
    @FXML
    public TableColumn<BookFx, String> amountBooksColumn;
    @FXML
    private TableColumn<BookFx, BookFx> deleteColumn;
    @FXML
    private TableColumn<BookFx, BookFx> editColumn;

    private ListBooksModel listBooksModel;

    @FXML
    public void initialize() {
        listBooksModel = new ListBooksModel();
        try {
            listBooksModel.initAllObservableList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        bindProperties();
    }

    private void bindProperties() {
        booksTableView.setItems(listBooksModel.getBookFxObservableList());
        titleColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().titleProperty());
        titleColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().titleProperty());
        descColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().descriptionProperty());
        ratingColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().ratingProperty());
        isbnColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().isbnProperty());
        releaseColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().releaseDateProperty());
        amountBooksColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().amounrtProperty());
        authorColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().authorFxObjectPropertyProperty());
        categoryColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().categoryFxObjectPropertyProperty());
        deleteColumn.setCellValueFactory(infoInCell -> new SimpleObjectProperty<>(infoInCell.getValue()));
        editColumn.setCellValueFactory(infoInCell -> new SimpleObjectProperty<>(infoInCell.getValue()));

        categoryComboBox.setItems(listBooksModel.getCategoryFxObservableList());
        authorComboBox.setItems(listBooksModel.getAuthorFxObservableList());

        listBooksModel.categoryFxObjectPropertyProperty().bind(categoryComboBox.valueProperty());
        listBooksModel.authorFxObjectPropertyProperty().bind(authorComboBox.valueProperty());
    }

    @FXML
    public void filterOnActionComboBox() {
    }

    @FXML
    public void clearCategoryComboBox() {
        categoryComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    public void clearAuthorComboBox() {
        authorComboBox.getSelectionModel().clearSelection();
    }


}
