package bonk_andrzej.app.fx.contollers;

import bonk_andrzej.app.fx.modelsFx.BooksListModel;
import bonk_andrzej.app.fx.view.AuthorFx;
import bonk_andrzej.app.fx.view.BookFx;
import bonk_andrzej.app.fx.view.CategoryFx;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.FxmlUtils;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class BooksListController {
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

    private BooksListModel booksListModel;

    @FXML
    private void initialize() {
        booksListModel = new BooksListModel();
        try {
            booksListModel.initAllObservableList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        bindProperties();
    }

    @FXML
    private void filterOnActionComboBox() {
        booksListModel.setFilterBooksList();
    }

    @FXML
    private void clearCategoryComboBox() {
        categoryComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void clearAuthorComboBox() {
        authorComboBox.getSelectionModel().clearSelection();
    }

    private void bindProperties() {
        booksTableView.setItems(booksListModel.getBookFxObservableList());
        titleColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().titleProperty());
        authorColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().authorFxProperty());
        descColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().descriptionProperty());

        ratingColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().ratingProperty());
        isbnColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().isbnProperty());
        releaseColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().releaseDateProperty());
        amountBooksColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().leftBooksForRentProperty());
        categoryColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().categoryFxProperty());
        deleteColumn.setCellValueFactory(infoInCell -> new SimpleObjectProperty<>(infoInCell.getValue()));
        editColumn.setCellValueFactory(infoInCell -> new SimpleObjectProperty<>(infoInCell.getValue()));

        categoryComboBox.setItems(booksListModel.getCategoryFxObservableList());
        authorComboBox.setItems(booksListModel.getAuthorFxObservableList());

        categoryComboBox.valueProperty().bindBidirectional(booksListModel.categoryFxProperty());
        authorComboBox.valueProperty().bindBidirectional(booksListModel.authorFxProperty());

        setDeleteColumn();
        setUpdateColumn();
    }

    private void setUpdateColumn() {
        editColumn.setCellFactory(param -> new TableCell<BookFx, BookFx>() {
            Button button = FxmlUtils.createButton(this.getClass(), "/icons/edit.png");

            @Override
            protected void updateItem(BookFx item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> {
                    FXMLLoader loader = FxmlUtils.getLoader("/fxml/AddBook.fxml");
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load());
                    } catch (IOException e) {
                        DialogsUtils.errorDialogs(e.getMessage());
                    }
                    BookController bookController = loader.getController();
                    bookController.getBookModel().setBookFxObjectProperty(item);
                    bookController.bindProperties();
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    initialize();
                });
            }
        });
    }

    private void setDeleteColumn() {
        deleteColumn.setCellFactory(param -> new TableCell<BookFx, BookFx>() {
            Button button = FxmlUtils.createButton(this.getClass(), "/icons/delete.png");

            @Override
            protected void updateItem(BookFx item, boolean empty) {
                super.updateItem(item, empty);
                button.setAlignment(Pos.CENTER);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> {
                    Optional<ButtonType> result = DialogsUtils.confirmAlert("delete.book.title", "delete.book.header");
                    if (result.get() == ButtonType.OK) {
                        try {
                            booksListModel.deleteBook(item);
                        } catch (ApplicationException e) {
                            DialogsUtils.errorDialogs(e.getMessage());
                        }
                    }
                });


            }
        });
    }

}
