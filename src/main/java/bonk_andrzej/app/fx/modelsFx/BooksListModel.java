package bonk_andrzej.app.fx.modelsFx;

import bonk_andrzej.app.db.dao.GenericCrud;
import bonk_andrzej.app.db.modelsDb.Author;
import bonk_andrzej.app.db.modelsDb.Book;
import bonk_andrzej.app.db.modelsDb.Category;
import bonk_andrzej.app.fx.view.AuthorFx;
import bonk_andrzej.app.fx.view.BookFx;
import bonk_andrzej.app.fx.view.CategoryFx;
import bonk_andrzej.app.utils.converter.AuthorConverter;
import bonk_andrzej.app.utils.converter.BookConverter;
import bonk_andrzej.app.utils.converter.CategoryConverter;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BooksListModel {
    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();

    private ObjectProperty<AuthorFx> authorFx = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFx> categoryFx = new SimpleObjectProperty<>();
    private List<BookFx> bookFxList = new ArrayList<>();

    private GenericCrud genericCrud = new GenericCrud();
    private AuthorConverter authorConverter = new AuthorConverter();
    private BookConverter bookConverter = new BookConverter();
    private CategoryConverter categoryConverter = new CategoryConverter();


    public void initAllObservableList() throws ApplicationException {
        initAuthorFxList();
        initCategoryFxList();
        initBookFxList();
    }

    private void initAuthorFxList() throws ApplicationException {
        authorFxObservableList.clear();
        List<Author> authors = genericCrud.getAll(Author.class);
        authors.forEach(author -> {
            AuthorFx authorFx = authorConverter
                    .convertAuthorToAuthorFx(author);
            authorFxObservableList.add(authorFx);
        });
    }

    private void initCategoryFxList() throws ApplicationException {
        categoryFxObservableList.clear();
        List<Category> categories = genericCrud.getAll(Category.class);
        categories.forEach(category -> {
            CategoryFx categoryFx = categoryConverter.convertCategoryToCategoryFx(category);
            categoryFxObservableList.add(categoryFx);
        });
    }

    private void initBookFxList() throws ApplicationException {
        bookFxList.clear();
        List<Book> books = genericCrud.getAll(Book.class);
        books.forEach(book -> bookFxList.add(bookConverter.convertBookToBookFx(book)));
        bookFxObservableList.setAll(bookFxList);
    }

    public void deleteBook(BookFx bookFx) throws ApplicationException {
        Book convertedBook = bookConverter.convertBookFxToBook(bookFx);
        Book bookToDelete = (Book) genericCrud.getById(Book.class, convertedBook.getId());
        genericCrud.delete(bookToDelete);

        initAllObservableList();
    }

    public void setFilterBooksList() {
        if (getAuthorFx() != null && getCategoryFx() != null) {
            filterBookList(authorPredicate().and(categoryPredicate()));
        } else if (getCategoryFx() != null) {
            filterBookList(categoryPredicate());
        } else if (getAuthorFx() != null) {
            filterBookList(authorPredicate());
        } else {
            bookFxObservableList.setAll(bookFxList);
        }
    }

    private void filterBookList(Predicate<BookFx> predicate) {
        List<BookFx> newList = bookFxList.stream().filter(predicate).collect(Collectors.toList());
        bookFxObservableList.setAll(newList);
    }

    private Predicate<BookFx> categoryPredicate() {
        return bookFx -> bookFx.getCategoryFx().getId()
                == getCategoryFx().getId();

    }

    private Predicate<BookFx> authorPredicate() {
        return bookFx -> bookFx.getAuthorFx().getId()
                == getAuthorFx().getId();
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

    public AuthorFx getAuthorFx() {
        return authorFx.get();
    }

    public ObjectProperty<AuthorFx> authorFxProperty() {
        return authorFx;
    }

    public void setAuthorFx(AuthorFx authorFx) {
        this.authorFx.set(authorFx);
    }

    public CategoryFx getCategoryFx() {
        return categoryFx.get();
    }

    public ObjectProperty<CategoryFx> categoryFxProperty() {
        return categoryFx;
    }

    public void setCategoryFx(CategoryFx categoryFx) {
        this.categoryFx.set(categoryFx);
    }

    public List<BookFx> getBookFxList() {
        return bookFxList;
    }

    public void setBookFxList(List<BookFx> bookFxList) {
        this.bookFxList = bookFxList;
    }


}
