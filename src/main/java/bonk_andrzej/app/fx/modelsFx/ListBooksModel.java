package bonk_andrzej.app.fx.modelsFx;

import bonk_andrzej.app.db.dao.CrudFacade;
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


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListBooksModel {
    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();

    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>();

    private CrudFacade crudFacade = new CrudFacade();
    private List<BookFx> bookFxList = new ArrayList<>();

    public void initAllObservableList() throws ApplicationException {
        initAuthorFxList();
        initCategoryFxList();
        initBookFxList();
    }

    public void deleteBook(BookFx bookFx) throws ApplicationException {
        Book book = BookConverter.convertFromBookFxToBook(bookFx);
        crudFacade.deleteO(book);
        initAllObservableList();
    }

    public void setFilterBooksList() throws ApplicationException {
        if (getAuthorFxObjectProperty() != null && getCategoryFxObjectProperty() != null) {
            filterBookListWithPredicates(authorPredicate().and(categoryPredicate()));
        } else if (getCategoryFxObjectProperty() != null) {
            filterBookListWithPredicates(categoryPredicate());
        } else if (getAuthorFxObjectProperty() != null) {
            filterBookListWithPredicates(authorPredicate());
        } else {
            this.bookFxObservableList.setAll(this.bookFxList);
        }
    }

    private void filterBookListWithPredicates(Predicate<BookFx> predicate) {
        List<BookFx> newList = bookFxList.stream().filter(predicate).collect(Collectors.toList());
        this.bookFxObservableList.setAll(newList);
    }

    private Predicate<BookFx> categoryPredicate() {
        Predicate<BookFx> predicate = bookFx -> bookFx.getCategoryFx().getId()
                == getCategoryFxObjectProperty().getId();
        return predicate;

    }

    private Predicate<BookFx> authorPredicate() {
        Predicate<BookFx> predicate = bookFx -> bookFx.getAuthorFx().getId()
                == getAuthorFxObjectProperty().getId();
        return predicate;
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
        bookFxList.clear();
        books.forEach(book -> {
            bookFxList.add(BookConverter.convertFromBookToBookFx(book));
        });
        bookFxObservableList.setAll(bookFxList);
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


}
