package app.fx.modelsFx;

import app.db.dao.CrudFacade;
import app.db.modelsDb.Author;
import app.db.modelsDb.Book;
import app.db.modelsDb.Category;
import app.utils.converter.AuthorConverter;
import app.utils.converter.BookConverter;
import app.utils.converter.CategoryConverter;
import app.utils.exceptions.ApplicationException;
import app.fx.view.AuthorFx;
import app.fx.view.BookFx;
import app.fx.view.CategoryFx;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ListBooksModel {
    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();

    //potrzebuje do przechpwywania cat lub authora przy filtrowaniu
    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>();

    private CrudFacade crudFacade = new CrudFacade();


    public void initAllObservableList() throws ApplicationException {
        initAuthorFxList();
        initCategoryFxList();
        initBookFxList();
    }


    private void initAuthorFxList() throws ApplicationException {
        List<Author> authors = crudFacade.getAll(Author.class);
        authorFxObservableList.clear();
        authors.forEach(author -> {
            AuthorFx authorFx = AuthorConverter
                    .convertFromAuthorToAuthorFx(author);
            authorFxObservableList.add(authorFx);
        });
    }

    private void initCategoryFxList() throws ApplicationException {
        List<Category> categories = crudFacade.getAll(Category.class);
        categoryFxObservableList.clear();
        categories.forEach(category -> {
            CategoryFx categoryFx = CategoryConverter.convertFromCategoryToCategoryFx(category);
            categoryFxObservableList.add(categoryFx);
        });
    }

    private void initBookFxList() throws ApplicationException {
        List<Book> books = crudFacade.getAll(Book.class);
        bookFxObservableList.clear();
        books.forEach(book -> {
            BookFx bookFx = BookConverter.convertFromBookToBookFx(book);
            bookFxObservableList.add(bookFx);
        });
    }

    private Predicate<BookFx> predicateCategory() {
        Predicate<BookFx> predicate = bookFx -> bookFx.getCategoryFxObjectProperty().getId()
                == getCategoryFxObjectProperty().getId();
        return predicate;

    }

    private Predicate<BookFx> predicateAuthor(){
        Predicate<BookFx> predicate = bookFx -> bookFx.getAuthorFxObjectProperty().getId()
                == getAuthorFxObjectProperty().getId();
        return predicate;
    }


    public ObservableList<BookFx> getBookFxObservableList() {
        return bookFxObservableList;
    }

    public void setBookFxObservableList(ObservableList<BookFx> bookFxObservableList) {
        this.bookFxObservableList = bookFxObservableList;
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public AuthorFx getAuthorFxObjectProperty() {
        return authorFxObjectProperty.get();
    }

    public ObjectProperty<AuthorFx> authorFxObjectPropertyProperty() {
        return authorFxObjectProperty;
    }

    public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {
        this.authorFxObjectProperty.set(authorFxObjectProperty);
    }

    public CategoryFx getCategoryFxObjectProperty() {
        return categoryFxObjectProperty.get();
    }

    public ObjectProperty<CategoryFx> categoryFxObjectPropertyProperty() {
        return categoryFxObjectProperty;
    }

    public void setCategoryFxObjectProperty(CategoryFx categoryFxObjectProperty) {
        this.categoryFxObjectProperty.set(categoryFxObjectProperty);
    }

    public List<BookFx> getBookFxList() {
        return bookFxList;
    }

    public void setBookFxList(List<BookFx> bookFxList) {
        this.bookFxList = bookFxList;
    }

    private List<BookFx> bookFxList = new ArrayList<>();
}
