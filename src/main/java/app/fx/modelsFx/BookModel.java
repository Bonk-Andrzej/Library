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

import java.util.List;

public class BookModel {
    private ObjectProperty<BookFx> bookFxObjectProperty = new SimpleObjectProperty<>(new BookFx());
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
    private CrudFacade crudFacade = new CrudFacade();

    public void initObservableCategoryListAndAuthorList() throws ApplicationException {
        initAuthorFxList();
        initCategoryFxList();
    }

    public void saveBookInDB() throws ApplicationException {
        Book book = BookConverter.convertFromBookFxToBook(getBookFxObjectProperty());
        Category category = (Category) crudFacade.getById(Category.class, getBookFxObjectProperty()
                .getCategoryFxObjectProperty().getId());
        Author author = (Author) crudFacade.getById(Author.class, getBookFxObjectProperty()
                .getAuthorFxObjectProperty().getId());
        book.setCategory(category);
        book.setAuthor(author);
        crudFacade.create(book);

    }

    private void initAuthorFxList() throws ApplicationException {
        List<Author> authors = crudFacade.getAll(Author.class);
        authorFxObservableList.clear();
        authors.forEach(a -> {
            AuthorFx authorFx = AuthorConverter
                    .convertFromAuthorToAuthorFx(a);
            authorFxObservableList.add(authorFx);
        });
    }

    private void initCategoryFxList() throws ApplicationException {
        List<Category> categories = crudFacade.getAll(Category.class);
        categoryFxObservableList.clear();
        categories.forEach(c -> {
            CategoryFx categoryFx = CategoryConverter.convertFromCategoryToCategoryFx(c);
            categoryFxObservableList.add(categoryFx);
        });
    }


    public BookFx getBookFxObjectProperty() {
        return bookFxObjectProperty.get();
    }

    public ObjectProperty<BookFx> bookFxObjectPropertyProperty() {
        return bookFxObjectProperty;
    }

    public void setBookFxObjectProperty(BookFx bookFxObjectProperty) {
        this.bookFxObjectProperty.set(bookFxObjectProperty);
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }
}
