package bonk_andrzej.app.fx.modelsFx;

import bonk_andrzej.app.db.dao.GenericCrud;
import bonk_andrzej.app.db.modelsDb.Book;
import bonk_andrzej.app.db.modelsDb.BookOrder;
import bonk_andrzej.app.db.modelsDb.Reader;
import bonk_andrzej.app.fx.view.BookFx;
import bonk_andrzej.app.fx.view.BookOrdersFx;
import bonk_andrzej.app.fx.view.ReaderFx;
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
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrdersListModel {

    private ObservableList<BookOrdersFx> bookOrdersFxObservableList = FXCollections.observableArrayList();
    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private ObservableList<ReaderFx> readerFxObservableList = FXCollections.observableArrayList();

    private ObjectProperty<ReaderFx> readerFx = new SimpleObjectProperty<>();
    private ObjectProperty<BookFx> bookFx = new SimpleObjectProperty<>();
    private List<BookOrdersFx> bookOrdersFxList = new ArrayList<>();

    private GenericCrud genericCrud = new GenericCrud();
    private BookConverter bookConverter = new BookConverter();
    private BooksOrderConverter booksOrderConverter = new BooksOrderConverter();
    private ReaderConverter readerConverter = new ReaderConverter();

    public void initAllObservableList() throws ApplicationException {
        initBookOrdersFxList();
        initReadersList();
        initBooksList();
    }

    private void initBookOrdersFxList() throws ApplicationException {
        bookOrdersFxList.clear();
        List<BookOrder> bookOrders = genericCrud.getAll(BookOrder.class);
        bookOrders.forEach(bookOrder -> bookOrdersFxList.add(booksOrderConverter.convertBookOrdersToBookOrdersFx(bookOrder)));
        bookOrdersFxObservableList.setAll(bookOrdersFxList);
    }

    private void initReadersList() throws ApplicationException {
        readerFxObservableList.clear();
        List<Reader> readers = genericCrud.getAll(Reader.class);
        readers.forEach(reader -> {
            ReaderFx readerFx = readerConverter
                    .convertReaderToReaderFx(reader);
            readerFxObservableList.add(readerFx);
        });
    }

    private void initBooksList() throws ApplicationException {
        bookFxObservableList.clear();
        List<Book> books = genericCrud.getAll(Book.class);
        books.forEach(book -> bookFxObservableList.add(bookConverter.convertBookToBookFx(book)));
    }

    public void deleteOrders(BookOrdersFx bookOrdersFx) throws ApplicationException {
        BookOrder bookOrder = booksOrderConverter.convertBookOrdersFxToBookOrders(bookOrdersFx);
        genericCrud.delete(bookOrder);
        initAllObservableList();
    }

    public void setFilterOrders() {
        if (getBookFx() != null && getReaderFx() != null) {
            filterOrderList(bookPredicate().and(readerPredicate()));
        } else if (getBookFx() != null) {
            filterOrderList(bookPredicate());
        } else if (getReaderFx() != null) {
            filterOrderList(readerPredicate());
        } else {
            bookOrdersFxObservableList.setAll(bookOrdersFxList);
        }
    }

    private void filterOrderList(Predicate<BookOrdersFx> predicate) {
        List<BookOrdersFx> newList = bookOrdersFxList.stream().filter(predicate).collect(Collectors.toList());
        bookOrdersFxObservableList.setAll(newList);
    }

    private Predicate<BookOrdersFx> bookPredicate() {
        return bookOrdersFx -> bookOrdersFx.getBookFx().getId()
                == getBookFx().getId();
    }

    private Predicate<BookOrdersFx> readerPredicate() {
        return bookOrdersFx -> bookOrdersFx.getReaderFx().getId()
                == getReaderFx().getId();
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

    public ReaderFx getReaderFx() {
        return readerFx.get();
    }

    public ObjectProperty<ReaderFx> readerFxProperty() {
        return readerFx;
    }

    public void setReaderFx(ReaderFx readerFx) {
        this.readerFx.set(readerFx);
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

    public List<BookOrdersFx> getBookOrdersFxList() {
        return bookOrdersFxList;
    }

    public void setBookOrdersFxList(List<BookOrdersFx> bookOrdersFxList) {
        this.bookOrdersFxList = bookOrdersFxList;
    }

}
