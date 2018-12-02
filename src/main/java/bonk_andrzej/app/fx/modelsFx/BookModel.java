package bonk_andrzej.app.fx.modelsFx;

import bonk_andrzej.app.db.dao.CrudFacade;
import bonk_andrzej.app.db.modelsDb.Author;
import bonk_andrzej.app.db.modelsDb.Book;
import bonk_andrzej.app.db.modelsDb.Category;
import bonk_andrzej.app.utils.converter.*;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import bonk_andrzej.app.fx.view.AuthorFx;
import bonk_andrzej.app.fx.view.BookFx;
import bonk_andrzej.app.fx.view.CategoryFx;
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
    private AuthorConverter authorConverter = new AuthorConverter();
    private BookConverter bookConverter = new BookConverter();
    private CategoryConverter categoryConverter = new CategoryConverter();


    public void initObservableCategoryListAndAuthorList() throws ApplicationException {
        initAuthorFxList();
        initCategoryFxList();
    }

    public void saveBookInDB() throws ApplicationException {
        Book book = bookConverter.convertBookFxToBook(getBookFxObjectProperty());
        crudFacade.createOrUpdate(book);
    }

    private void initAuthorFxList() throws ApplicationException {
        List<Author> authors = crudFacade.getAll(Author.class);
        authorFxObservableList.clear();
        authors.forEach(author -> {
            AuthorFx authorFx = authorConverter
                    .convertAuthorToAuthorFx(author);
            authorFxObservableList.add(authorFx);
        });
    }

    private void initCategoryFxList() throws ApplicationException {
        List<Category> categories = crudFacade.getAll(Category.class);
        categoryFxObservableList.clear();
        categories.forEach(category -> {
            CategoryFx categoryFx = categoryConverter.convertCategoryToCategoryFx(category);
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
