package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.dao.CrudFacade;
import bonk_andrzej.app.db.modelsDb.Book;
import bonk_andrzej.app.db.modelsDb.BookOrder;
import bonk_andrzej.app.db.modelsDb.Reader;
import bonk_andrzej.app.fx.view.BookFx;
import bonk_andrzej.app.fx.view.BookOrdersFx;
import bonk_andrzej.app.utils.exceptions.ApplicationException;

import java.util.ArrayList;
import java.util.List;

public class BooksOrdersConverter {
    private BookConverter bookConverter = new BookConverter();
    private ReaderConverter readerConverter = new ReaderConverter();


    public BookOrder convertBookOrdersFxToBookOrders(BookOrdersFx bookOrdersFx) {

        BookOrder bookOrder = new BookOrder();
        bookOrder.setId(bookOrdersFx.getId());

        bookOrder.setAmountAllBorrowedBooks(Integer.valueOf(bookOrdersFx.getAmountAllBorrowedBooks()));
        bookOrder.setAmountReturnedBooksNow(calculateBooksReturnedNow(bookOrdersFx));
        bookOrder.setAllReturnedBooks(calculateAllReturnedBooks(bookOrder));
        bookOrder.setAmountBooksToReturn(calculateBooksToReturn(bookOrder));

        bookOrder.setLenderDate(bookOrdersFx.getLenderDate());
        bookOrder.setReturnDate(bookOrdersFx.getReturnDate());
        bookOrder.setActualDateOfReturn(bookOrdersFx.getActualDateOfReturn());

        return bookOrder;
    }

    public BookOrdersFx convertBookOrdersToBookOrdersFx(BookOrder bookOrder) {
        BookOrdersFx bookOrdersFx = new BookOrdersFx();
        bookOrdersFx.setId(bookOrder.getId());

        bookOrdersFx.setAmountAllBorrowedBooks(String.valueOf(bookOrder.getAmountAllBorrowedBooks()));
        bookOrdersFx.setAmountBooksToReturn(bookOrder.getAmountBooksToReturn());
        bookOrdersFx.setAmountAllReturnedBooks(bookOrder.getAllReturnedBooks());

        bookOrdersFx.setLenderDate(bookOrder.getLenderDate());
        bookOrdersFx.setReturnDate(bookOrder.getReturnDate());
        bookOrdersFx.setActualDateOfReturn(bookOrder.getActualDateOfReturn());

        List<Book> bookOrdersList = new ArrayList<>(bookOrder.getBookList());
        List<BookFx> bookFxList = new ArrayList<>();
        bookOrdersList.forEach(book -> {
            BookFx bookFx = (bookConverter.convertBookToBookFx(book));
            bookFxList.add(bookFx);
            bookOrdersFx.setCategoryFx(bookFx.getCategoryFx());
            bookOrdersFx.setAuthorFx(bookFx.getAuthorFx());
            bookOrdersFx.setBookTitle(bookFx.getTitle());
        });
        bookOrdersFx.setBookFxList(bookFxList);
        bookOrdersFx.setReaderFx(readerConverter.convertReaderToReaderFx(bookOrder.getReader()));
        return bookOrdersFx;
    }


    private int calculateAllReturnedBooks(BookOrder bookOrder) {
        int allReturnedBefore;
        //todo moze doddac optionale
        if (bookOrder.getAllReturnedBooks() == null) {
            allReturnedBefore = 0;
        } else {
            allReturnedBefore = bookOrder.getAllReturnedBooks();
        }
        int returnedBooksNow = bookOrder.getAmountReturnedBooksNow();
        int allReturned = allReturnedBefore + returnedBooksNow;
        return allReturned;
    }

    private int calculateBooksToReturn(BookOrder bookOrder) {
        int allBorrowedBooks = bookOrder.getAmountAllBorrowedBooks();
        int allReturnedBefore = bookOrder.getAllReturnedBooks();
        int booksToReturn = allBorrowedBooks - allReturnedBefore;
        System.out.println(booksToReturn + " ksiazki do zwroptu");
        return booksToReturn;
    }

    private int calculateBooksReturnedNow(BookOrdersFx bookOrdersFx) {
        int returnedNow;
        if (bookOrdersFx.getAmountReturnedBooksNow() == null) {
            returnedNow = 0;
        } else {
            returnedNow = Integer.parseInt(bookOrdersFx.getAmountReturnedBooksNow());
        }
        return returnedNow;
    }

}
