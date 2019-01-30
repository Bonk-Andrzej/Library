package bonk_andrzej.app.fx.view;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Objects;

public class BookFx {

    private ObjectProperty<CategoryFx> categoryFx = new SimpleObjectProperty<>();
    private ObjectProperty<AuthorFx> authorFx = new SimpleObjectProperty();
    private LongProperty id = new SimpleLongProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private IntegerProperty rating = new SimpleIntegerProperty();
    private StringProperty isbn = new SimpleStringProperty();
    private StringProperty leftBooksForRent = new SimpleStringProperty();
    private ObjectProperty<LocalDate> releaseDate = new SimpleObjectProperty(LocalDate.now());


    public CategoryFx getCategoryFx() {
        return categoryFx.get();
    }

    public ObjectProperty<CategoryFx> categoryFxProperty() {
        return categoryFx;
    }

    public void setCategoryFx(CategoryFx categoryFx) {
        this.categoryFx.set(categoryFx);
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

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getRating() {
        return rating.get();
    }

    public IntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public LocalDate getReleaseDate() {
        return releaseDate.get();
    }

    public ObjectProperty<LocalDate> releaseDateProperty() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate.set(releaseDate);
    }

    public String getLeftBooksForRent() {
        return leftBooksForRent.get();
    }

    public StringProperty leftBooksForRentProperty() {
        return leftBooksForRent;
    }

    public void setLeftBooksForRent(String leftBooksForRent) {
        this.leftBooksForRent.set(leftBooksForRent);
    }

    @Override
    public String toString() {
        return "TITLE: " + title.get() +
                ", CATEGORY: " + categoryFx.get() +
                ", AUTHOR: " + authorFx.get() +
                ", AMOUNT: " + leftBooksForRent.get();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookFx bookFx = (BookFx) o;
        return this.authorFxProperty().get().equals(((BookFx)o).authorFx.get()) &&
                Objects.equals(authorFx, bookFx.authorFx) &&
                Objects.equals(id, bookFx.id) &&
                Objects.equals(title, bookFx.title) &&
                Objects.equals(description, bookFx.description) &&
                Objects.equals(rating, bookFx.rating) &&
                Objects.equals(isbn, bookFx.isbn) &&
                Objects.equals(leftBooksForRent, bookFx.leftBooksForRent) &&
                Objects.equals(releaseDate, bookFx.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryFx, authorFx, id, title, description, rating, isbn, leftBooksForRent, releaseDate);
    }
}
