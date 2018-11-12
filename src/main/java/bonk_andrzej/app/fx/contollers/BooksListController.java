package bonk_andrzej.app.fx.contollers;

import bonk_andrzej.app.fx.modelsFx.ListBooksModel;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.time.LocalDate;

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

    @FXML
    public void filterOnActionComboBox() throws ApplicationException {
        listBooksModel.setFilterBooksList();
    }

    @FXML
    public void clearCategoryComboBox() {
        categoryComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    public void clearAuthorComboBox() {
        authorComboBox.getSelectionModel().clearSelection();
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
        authorColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().authorFxProperty());
        categoryColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().categoryFxProperty());
        deleteColumn.setCellValueFactory(infoInCell -> new SimpleObjectProperty<>(infoInCell.getValue()));
        editColumn.setCellValueFactory(infoInCell -> new SimpleObjectProperty<>(infoInCell.getValue()));

        categoryComboBox.setItems(listBooksModel.getCategoryFxObservableList());
        authorComboBox.setItems(listBooksModel.getAuthorFxObservableList());

        listBooksModel.categoryFxObjectPropertyProperty().bind(categoryComboBox.valueProperty());
        listBooksModel.authorFxObjectPropertyProperty().bind(authorComboBox.valueProperty());

        setDeleteColumn();
        setUpdateColumn();
    }

    private void setUpdateColumn() {
        editColumn.setCellFactory(param -> new TableCell<BookFx, BookFx>() {
            Button button = createButton("/icons/edit.png");

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
                });
            }
        });
    }

    private void setDeleteColumn() {
        deleteColumn.setCellFactory(param -> new TableCell<BookFx, BookFx>() {
            Button button = createButton("/icons/delete.png");

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
                    try {
                        listBooksModel.deleteBook(item);
                    } catch (ApplicationException e) {
                        DialogsUtils.errorDialogs(e.getMessage());
                    }
                });
            }
        });
    }

    private Button createButton(String path) {
        Button button = new Button();
        Image image = new Image(this.getClass().getResource(path).toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }
}
