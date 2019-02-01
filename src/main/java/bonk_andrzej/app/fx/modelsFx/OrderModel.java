package bonk_andrzej.app.fx.modelsFx;

import bonk_andrzej.app.db.dao.GenericCrud;
import bonk_andrzej.app.db.modelsDb.Book;
import bonk_andrzej.app.db.modelsDb.BookOrder;
import bonk_andrzej.app.db.modelsDb.Reader;
import bonk_andrzej.app.fx.view.BookFx;
import bonk_andrzej.app.fx.view.BookOrdersFx;
import bonk_andrzej.app.fx.view.ReaderFx;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.converter.BookConverter;
import bonk_andrzej.app.utils.converter.BooksOrderConverter;
import bonk_andrzej.app.utils.converter.ReaderConverter;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    private ObjectProperty<BookOrdersFx> bookOrdersFxObjectProperty = new SimpleObjectProperty<>(new BookOrdersFx());
    private ObservableList<BookOrdersFx> bookOrdersFxObservableList = FXCollections.observableArrayList();
    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private ObservableList<ReaderFx> readerFxObservableList = FXCollections.observableArrayList();
    private GenericCrud genericCrud = new GenericCrud();
    private BookConverter bookConverter = new BookConverter();
    private BooksOrderConverter booksOrderConverter = new BooksOrderConverter();
    private ReaderConverter readerConverter = new ReaderConverter();
    private Book book;
    private Reader reader;
    private BookOrder bookOrder;

    public void initAllObservableList() throws ApplicationException {
        initObservableBookList();
        initObservableReaderList();
    }

    private void initObservableBookList() throws ApplicationException {
        List<Book> books = genericCrud.getAll(Book.class);
        bookFxObservableList.clear();
        books.forEach(book -> {
            BookFx bookFx = bookConverter.convertBookToBookFx(book);
            bookFxObservableList.add(bookFx);
        });
    }

    private void initObservableReaderList() throws ApplicationException {
        List<Reader> readers = genericCrud.getAll(Reader.class);
        readerFxObservableList.clear();
        readers.forEach(reader -> {
            ReaderFx readerFx = readerConverter.convertReaderToReaderFx(reader);
            readerFxObservableList.add(readerFx);
        });
    }

    public void updateOrderInDB() throws ApplicationException {
        bookOrder = (BookOrder) genericCrud.getById(BookOrder.class, getBookOrdersFxObjectProperty().getId());
        book = (Book) genericCrud.getById(Book.class, getBookOrdersFxObjectProperty().getBookFx().getId());
        reader = (Reader) genericCrud.getById(Reader.class, getBookOrdersFxObjectProperty().getReaderFx().getId());
        int allToReturnBefore = bookOrder.getAmountBooksToReturn();
        int returnedBooksNow = Integer.parseInt(getBookOrdersFxObjectProperty().getAmountReturnedBooksNow());
        if (isGoodAmountReturnedBooks(allToReturnBefore, returnedBooksNow)) {
            int leftBooksForRent = book.getLeftBooksForRent();
            leftBooksForRent += returnedBooksNow;

            int returnedBooksBefore = bookOrder.getAllReturnedBooks();
            int allReturnedNow = returnedBooksBefore + returnedBooksNow;
            int allBorrowedBooks = bookOrder.getAmountAllBorrowedBooks();
            int allBooksToReturnNow = allBorrowedBooks - allReturnedNow;

            updateBookOrder(bookOrder, allReturnedNow, allBooksToReturnNow, reader, book);
            updateBook(book, bookOrder, leftBooksForRent);
            genericCrud.createOrUpdate(bookOrder);
            initAllObservableList();
        }
    }

    public void saveOrderToDB() throws ApplicationException {
        book = (Book) genericCrud.getById(Book.class, getBookOrdersFxObjectProperty().getBookFx().getId());
        int allBorrowedBooks = Integer.parseInt(getBookOrdersFxObjectProperty().getAmountAllBorrowedBooks());
        int returnedBooks = Integer.parseInt(getBookOrdersFxObjectProperty().getAmountReturnedBooksNow());
        if (isBooksLeftForBorrow(book, allBorrowedBooks, returnedBooks)) {
            bookOrder = booksOrderConverter.convertBookOrdersFxToBookOrders(getBookOrdersFxObjectProperty());
            reader = (Reader) genericCrud.getById(Reader.class, getBookOrdersFxObjectProperty().getReaderFx().getId());

            if (isGoodAmountReturnedBooks(allBorrowedBooks, returnedBooks)) {
                int amountAllBorrowedBooks = bookOrder.getAmountAllBorrowedBooks();
                int allReturned = bookOrder.getAllReturnedBooks();
                int leftBooksForRent = book.getLeftBooksForRent();
                int bookForReturnNow = amountAllBorrowedBooks - allReturned;
                leftBooksForRent -= bookForReturnNow;

                updateBook(book, bookOrder, leftBooksForRent);
                updateBookOrder(bookOrder, allReturned, bookForReturnNow, reader, book);
                genericCrud.createOrUpdate(bookOrder);
                initAllObservableList();
            }
        }
    }

    private void updateBookOrder(BookOrder bookOrder, int allReturnedNow, int allBooksToReturnNow, Reader reader, Book book) {
        bookOrder.setAllReturnedBooks(allReturnedNow);
        bookOrder.setAmountBooksToReturn(allBooksToReturnNow);
        bookOrder.setReader(reader);
        bookOrder.setBook(book);
    }


    private void updateBook(Book book, BookOrder bookOrder, int leftBooksForRent) throws ApplicationException {
        book.setLeftBooksForRent(leftBooksForRent);
        List<BookOrder> bookOrderList = new ArrayList<>();
        bookOrderList.add(bookOrder);
        book.setBookOrders(bookOrderList);
        genericCrud.createOrUpdate(book);
    }

    private boolean isGoodAmountReturnedBooks(int amountToReturn, int returnedBooks) {
        if (amountToReturn >= returnedBooks) {
            return true;
        } else {
            DialogsUtils.specificBooksOrdersError("too.many.books.returned");
            return false;
        }
    }

    private boolean isBooksLeftForBorrow(Book book, int allBorrowedBooks, int returnedBooks) {
        int leftBooksForRent = book.getLeftBooksForRent();
        allBorrowedBooks -= returnedBooks;
        if (leftBooksForRent >= allBorrowedBooks) {
            return true;
        } else {
            DialogsUtils.specificBooksOrdersError("error.not.enough.books");
            return false;
        }
    }

    public BookOrdersFx getBookOrdersFxObjectProperty() {
        return bookOrdersFxObjectProperty.get();
    }

    public ObjectProperty<BookOrdersFx> bookOrdersFxObjectPropertyProperty() {
        return bookOrdersFxObjectProperty;
    }

    public void setBookOrdersFxObjectProperty(BookOrdersFx bookOrdersFxObjectProperty) {
        this.bookOrdersFxObjectProperty.set(bookOrdersFxObjectProperty);
    }

    public ObservableList<BookFx> getBookFxObservableList() {
        return bookFxObservableList;
    }

    public void setBookFxObservableList(ObservableList<BookFx> bookFxObservableList) {
        this.bookFxObservableList = bookFxObservableList;
    }

    public ObservableList<ReaderFx> getReaderFxObservableList() {
        return readerFxObservableList;
    }

    public void setReaderFxObservableList(ObservableList<ReaderFx> readerFxObservableList) {
        this.readerFxObservableList = readerFxObservableList;
    }

    public ObservableList<BookOrdersFx> getBookOrdersFxObservableList() {
        return bookOrdersFxObservableList;
    }

    public void setBookOrdersFxObservableList(ObservableList<BookOrdersFx> bookOrdersFxObservableList) {
        this.bookOrdersFxObservableList = bookOrdersFxObservableList;
    }
}