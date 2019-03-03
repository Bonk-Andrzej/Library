package bonk_andrzej.app.fx.modelFx;

import bonk_andrzej.app.db.dao.GenericCrud;
import bonk_andrzej.app.db.modelDb.Book;
import bonk_andrzej.app.db.modelDb.Reader;
import bonk_andrzej.app.fx.view.BookFx;
import bonk_andrzej.app.fx.view.ReaderFx;
import bonk_andrzej.app.utils.converter.BookConverter;
import bonk_andrzej.app.utils.converter.ReaderConverter;
import bonk_andrzej.app.utils.exception.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ReaderModel {
    private ObjectProperty<ReaderFx> readerFxObjectProperty = new SimpleObjectProperty<>(new ReaderFx());
    private ObservableList<ReaderFx> readerFxObservableList = FXCollections.observableArrayList();
    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private GenericCrud genericCrud = new GenericCrud();
    private BookConverter bookConverter = new BookConverter();
    private ReaderConverter readerConverter = new ReaderConverter();


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

    public void initObservableReaderList() throws ApplicationException {
        List<Reader> readers = genericCrud.getAll(Reader.class);
        readerFxObservableList.clear();
        readers.forEach(reader -> {
            ReaderFx readerFx = readerConverter.convertReaderToReaderFx(reader);
            readerFxObservableList.add(readerFx);
        });
    }

    public void saveOrUpdateReaderToDB() throws ApplicationException {
        Reader reader = readerConverter.convertReaderFxToReader(getReaderFxObjectProperty());
        genericCrud.createOrUpdate(reader);
        initObservableReaderList();
    }

    public void deleteAuthorInDB() throws ApplicationException {
        Reader authorToDelete = (Reader) genericCrud.getById(
                Reader.class, getReaderFxObjectProperty().getId());
        genericCrud.delete(authorToDelete);
        initObservableReaderList();
    }


    public ReaderFx getReaderFxObjectProperty() {
        return readerFxObjectProperty.get();
    }

    public ObjectProperty<ReaderFx> readerFxObjectPropertyProperty() {
        return readerFxObjectProperty;
    }

    public void setReaderFxObjectProperty(ReaderFx readerFxObjectProperty) {
        this.readerFxObjectProperty.set(readerFxObjectProperty);
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
}
