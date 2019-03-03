package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelDb.*;
import bonk_andrzej.app.fx.view.*;

import java.time.LocalDate;

abstract class AbstractTest {
    private final long idForConvert = 1L;
    private final String nameForConvert = "Andrzej";
    private final String surnameForConvert = "Bonk";
    private final String categoryName = "Kryminał";
    private final String titleForConvert = "Ogniem i mieczem";
    private final String descriptionForConvert = "Książka w ktorej walcza i na miecze i na ogień";
    private final int ratingForConvert = 5;
    private final String isbnForConvert = "ISBN-13: 978-83-900210-1-0";
    private final String amountLeftBooksForConvert = "5";
    private final LocalDate releaseDateForConvert = LocalDate.of(2010, 10, 10);
    private final LocalDate lenderDate = LocalDate.of(2010, 10, 10);
    private final LocalDate returnDate = LocalDate.of(2010, 10, 10);
    private final LocalDate actualDateOfReturn = LocalDate.of(2010, 10, 10);


    AuthorFx createAuthorFx() {
        AuthorFx authorFx = new AuthorFx();
        authorFx.setName(nameForConvert);
        authorFx.setSurname(surnameForConvert);
        authorFx.setId(idForConvert);
        return authorFx;
    }

    Author createAuthor() {
        Author author = new Author();
        author.setName(nameForConvert);
        author.setSurname(surnameForConvert);
        author.setId(idForConvert);
        return author;
    }

    Reader createReader() {
        Reader reader = new Reader();
        reader.setId(idForConvert);
        reader.setName(nameForConvert);
        reader.setSurname(surnameForConvert);
        return reader;
    }

    ReaderFx createReaderFx() {
        ReaderFx readerFx = new ReaderFx();
        readerFx.setId(idForConvert);
        readerFx.setName(nameForConvert);
        readerFx.setSurname(surnameForConvert);
        return readerFx;
    }

    Category createCategory() {
        Category category = new Category();
        category.setId(idForConvert);
        category.setName(categoryName);
        return category;
    }

    CategoryFx createCategoryFx() {
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(idForConvert);
        categoryFx.setName(categoryName);
        return categoryFx;
    }

    Book createBook() {
        Book book = new Book();
        book.setId(idForConvert);
        book.setTitle(titleForConvert);
        book.setDescription(descriptionForConvert);
        book.setRating(ratingForConvert);
        book.setIsbn(isbnForConvert);
        book.setLeftBooksForRent(Integer.valueOf(amountLeftBooksForConvert));
        book.setReleaseDate(releaseDateForConvert);

        book.setCategory(createCategory());
        book.setAuthor(createAuthor());
        return book;
    }

    BookFx createBookFx() {
        BookFx bookFx = new BookFx();
        bookFx.setId(idForConvert);
        bookFx.setTitle(titleForConvert);
        bookFx.setDescription(descriptionForConvert);
        bookFx.setRating(ratingForConvert);
        bookFx.setIsbn(isbnForConvert);
        bookFx.setLeftBooksForRent(amountLeftBooksForConvert);
        bookFx.setReleaseDate(releaseDateForConvert);

        bookFx.setCategoryFx(createCategoryFx());
        bookFx.setAuthorFx(createAuthorFx());
        return bookFx;
    }

    BookOrder createBookOrder() {
        BookOrder bookOrder = new BookOrder();
        bookOrder.setId(idForConvert);
        bookOrder.setAmountAllBorrowedBooks(5);
        bookOrder.setAmountReturnedBooksNow(0);
        bookOrder.setAllReturnedBooks(0);
        bookOrder.setAmountBooksToReturn(5);


        bookOrder.setLenderDate(lenderDate);
        bookOrder.setReturnDate(returnDate);
        bookOrder.setActualDateOfReturn(actualDateOfReturn);

        return bookOrder;
    }

    BookOrdersFx createBookOrderFx() {
        BookOrdersFx bookOrdersFx = new BookOrdersFx();
        bookOrdersFx.setId(idForConvert);

        bookOrdersFx.setAmountAllBorrowedBooks(String.valueOf(5));
        bookOrdersFx.setAmountBooksToReturn(3);
        bookOrdersFx.setAmountAllReturnedBooks(2);

        bookOrdersFx.setLenderDate(lenderDate);
        bookOrdersFx.setReturnDate(returnDate);
        bookOrdersFx.setActualDateOfReturn(actualDateOfReturn);

        return bookOrdersFx;
    }

}
