package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelDb.BookOrder;
import bonk_andrzej.app.fx.view.BookOrdersFx;

public class BooksOrderConverter {
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
        bookOrder.setReader(readerConverter.convertReaderFxToReader(bookOrdersFx.getReaderFx()));

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

        bookOrdersFx.setBookFx(bookConverter.convertBookToBookFx(bookOrder.getBook()));
        bookOrdersFx.setCategoryFx(bookOrdersFx.getBookFx().getCategoryFx());
        bookOrdersFx.setAuthorFx(bookOrdersFx.getBookFx().getAuthorFx());
        bookOrdersFx.setBookTitle(bookOrdersFx.getBookFx().getTitle());
        bookOrdersFx.setReaderFx(readerConverter.convertReaderToReaderFx(bookOrder.getReader()));

        return bookOrdersFx;
    }


    private int calculateAllReturnedBooks(BookOrder bookOrder) {
        int allReturnedBefore;
        if (bookOrder.getAllReturnedBooks() == null) {
            allReturnedBefore = 0;
        } else {
            allReturnedBefore = bookOrder.getAllReturnedBooks();
        }
        int returnedBooksNow = bookOrder.getAmountReturnedBooksNow();
        return allReturnedBefore + returnedBooksNow;
    }

    private int calculateBooksToReturn(BookOrder bookOrder) {
        int allBorrowedBooks = bookOrder.getAmountAllBorrowedBooks();
        int allReturnedBefore = bookOrder.getAllReturnedBooks();
        return allBorrowedBooks - allReturnedBefore;
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
