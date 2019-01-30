package bonk_andrzej.app.fx.view;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Objects;

public class BookOrdersFx {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty bookTitle = new SimpleStringProperty();
    private StringProperty amountAllBorrowedBooks = new SimpleStringProperty();
    private StringProperty amountReturnedBooksNow = new SimpleStringProperty();
    private IntegerProperty amountBooksToReturn = new SimpleIntegerProperty();
    private IntegerProperty amountAllReturnedBooks = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> lenderDate = new SimpleObjectProperty<>(LocalDate.now());
    private ObjectProperty<LocalDate> returnDate = new SimpleObjectProperty<>(LocalDate.now().plusMonths(3));
    private ObjectProperty<LocalDate> actualDateOfReturn = new SimpleObjectProperty<>(LocalDate.now());

    private ObjectProperty<ReaderFx> readerFx = new SimpleObjectProperty<>();
    private ObjectProperty<BookFx> bookFx = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFx> categoryFx = new SimpleObjectProperty<>();
    private ObjectProperty<AuthorFx> authorFx = new SimpleObjectProperty<>();


    public String getBookTitle() {
        return bookTitle.get();
    }

    public StringProperty bookTitleProperty() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    public BookFx getBookFx() {
        return bookFx.get();
    }

    public ObjectProperty<BookFx> bookFxProperty() {
        return bookFx;
    }

    public void setBookFx(BookFx bookFx) {
        this.bookFx.set(bookFx);
    }

    public ReaderFx getReaderFx() {
        return readerFx.get();
    }

    public ObjectProperty<ReaderFx> readerFxProperty() {
        return readerFx;
    }

    public void setReaderFx(ReaderFx readerFx) {
        this.readerFx.set(readerFx);
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

    public String getAmountAllBorrowedBooks() {
        return amountAllBorrowedBooks.get();
    }

    public StringProperty amountAllBorrowedBooksProperty() {
        return amountAllBorrowedBooks;
    }

    public void setAmountAllBorrowedBooks(String amountAllBorrowedBooks) {
        this.amountAllBorrowedBooks.set(amountAllBorrowedBooks);
    }

    public int getAmountBooksToReturn() {
        return amountBooksToReturn.get();
    }

    public IntegerProperty amountBooksToReturnProperty() {
        return amountBooksToReturn;
    }

    public void setAmountBooksToReturn(int amountBooksToReturn) {
        this.amountBooksToReturn.set(amountBooksToReturn);
    }

    public String getAmountReturnedBooksNow() {
        return amountReturnedBooksNow.get();
    }

    public StringProperty amountReturnedBooksNowProperty() {
        return amountReturnedBooksNow;
    }

    public void setAmountReturnedBooksNow(String amountReturnedBooksNow) {
        this.amountReturnedBooksNow.set(amountReturnedBooksNow);
    }

    public LocalDate getLenderDate() {
        return lenderDate.get();
    }

    public ObjectProperty<LocalDate> lenderDateProperty() {
        return lenderDate;
    }

    public void setLenderDate(LocalDate lenderDate) {
        this.lenderDate.set(lenderDate);
    }

    public LocalDate getReturnDate() {
        return returnDate.get();
    }

    public ObjectProperty<LocalDate> returnDateProperty() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate.set(returnDate);
    }

    public LocalDate getActualDateOfReturn() {
        return actualDateOfReturn.get();
    }

    public ObjectProperty<LocalDate> actualDateOfReturnProperty() {
        return actualDateOfReturn;
    }

    public void setActualDateOfReturn(LocalDate actualDateOfReturn) {
        this.actualDateOfReturn.set(actualDateOfReturn);
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

    public int getAmountAllReturnedBooks() {
        return amountAllReturnedBooks.get();
    }

    public IntegerProperty amountAllReturnedBooksProperty() {
        return amountAllReturnedBooks;
    }

    public void setAmountAllReturnedBooks(int amountAllReturnedBooks) {
        this.amountAllReturnedBooks.set(amountAllReturnedBooks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookOrdersFx that = (BookOrdersFx) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(bookTitle, that.bookTitle) &&
                Objects.equals(amountAllBorrowedBooks, that.amountAllBorrowedBooks) &&
                Objects.equals(amountReturnedBooksNow, that.amountReturnedBooksNow) &&
                Objects.equals(amountBooksToReturn, that.amountBooksToReturn) &&
                Objects.equals(amountAllReturnedBooks, that.amountAllReturnedBooks) &&
                Objects.equals(lenderDate, that.lenderDate) &&
                Objects.equals(returnDate, that.returnDate) &&
                Objects.equals(actualDateOfReturn, that.actualDateOfReturn) &&
                Objects.equals(readerFx, that.readerFx) &&
                Objects.equals(bookFx, that.bookFx) &&
                Objects.equals(categoryFx, that.categoryFx) &&
                Objects.equals(authorFx, that.authorFx);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookTitle, amountAllBorrowedBooks, amountReturnedBooksNow, amountBooksToReturn, amountAllReturnedBooks, lenderDate, returnDate, actualDateOfReturn, readerFx, bookFx, categoryFx, authorFx);
    }
}
