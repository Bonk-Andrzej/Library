package bonk_andrzej.app.fx.modelsFx;

import bonk_andrzej.app.db.dao.CrudFacade;
import bonk_andrzej.app.db.modelsDb.*;
import bonk_andrzej.app.fx.view.*;
import bonk_andrzej.app.utils.converter.*;
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

    private CrudFacade crudFacade = new CrudFacade();
    private BookConverter bookConverter = new BookConverter();
    private BooksOrdersConverter booksOrdersConverter = new BooksOrdersConverter();
    private ReaderConverter readerConverter = new ReaderConverter();

    public void initAllObservableList() throws ApplicationException {
        initBookFxList();
        initReadersList();
        initOrdersList();
    }

    private void initBookFxList() throws ApplicationException {
        bookFxObservableList.clear();
        List<Book> books = crudFacade.getAll(Book.class);
        books.forEach(book -> bookFxObservableList.add(bookConverter.convertBookToBookFx(book)));
    }

    private void initReadersList() throws ApplicationException {
        List<Reader> readers = crudFacade.getAll(Reader.class);
        readerFxObservableList.clear();
        readers.forEach(reader -> {
            ReaderFx readerFx = readerConverter
                    .convertReaderToReaderFx(reader);
            readerFxObservableList.add(readerFx);
        });
    }

    private void initOrdersList() throws ApplicationException {
        bookOrdersFxObservableList.clear();
        List<BookOrder> orders = crudFacade.getAll(BookOrder.class);
        orders.forEach(order -> {
            BookOrdersFx bookOrdersFx = booksOrdersConverter.convertBookOrdersToBookOrdersFx(order);
            bookOrdersFx.setCategoryFx(bookOrdersFx.getCategoryFx());
            bookOrdersFx.setAuthorFx(bookOrdersFx.getAuthorFx());
            bookOrdersFx.setBookFx(bookOrdersFx.getBookFx());
            bookOrdersFxObservableList.add(bookOrdersFx);
        });
    }

    public void deleteOrders(BookOrdersFx bookOrdersFx) throws ApplicationException {
        BookOrder bookOrder = booksOrdersConverter.convertBookOrdersFxToBookOrders(bookOrdersFx);
        crudFacade.delete(bookOrder);
        initAllObservableList();
    }

    //todo do poprawy filtrowanie
    public void setFilterReaders() {
        if (getBookFx() != null && getReaderFx() != null) {
            filterReaderList(titlePredicate().and(readerPredicate()));
        } else if (getBookFx() != null) {
            filterReaderList(titlePredicate());
        } else if (getReaderFx() != null) {
            filterReaderList(readerPredicate());
        } else {
            bookOrdersFxObservableList.setAll(bookOrdersFxList);
        }
    }

    private void filterReaderList(Predicate<BookOrdersFx> predicate) {
        List<BookOrdersFx> newList = bookOrdersFxList.stream().filter(predicate).collect(Collectors.toList());
        bookOrdersFxObservableList.setAll(newList);
    }

    private Predicate<BookOrdersFx> titlePredicate() {
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

//    public BookOrdersFx getBookOrdersFxObjectProperty() {
//        return bookOrdersFxObjectProperty.get();
//    }
//
//    public ObjectProperty<BookOrdersFx> bookOrdersFxObjectPropertyProperty() {
//        return bookOrdersFxObjectProperty;
//    }
//
//    public void setBookOrdersFxObjectProperty(BookOrdersFx bookOrdersFxObjectProperty) {
//        this.bookOrdersFxObjectProperty.set(bookOrdersFxObjectProperty);
//    }
}
