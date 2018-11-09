package app.utils.converter;

import app.db.modelsDb.Book;
import app.fx.view.BookFx;
import app.utils.Utils;

public class BookConverter {

    public static Book convertFromBookFxToBook(BookFx bookFx) {
        Book book = new Book();
        book.setId(bookFx.getId());
        book.setTitle(bookFx.getTitle());
        book.setDescription(bookFx.getDescription());
        book.setRating(bookFx.getRating());
        book.setIsbn(bookFx.getIsbn());
        book.setAmount(bookFx.getAmounrt());
        book.setReleaseDate(Utils.convertToDate(bookFx.getReleaseDate()));
        book.setAddedDate(Utils.convertToDate(bookFx.getAddedDate()));
        return book;
    }
   
    public static BookFx convertFromBookToBookFx(Book book) {
        BookFx bookFx = new BookFx();
        bookFx.setId(book.getId());
        bookFx.setTitle(book.getTitle());
        bookFx.setDescription(book.getDescription());
        bookFx.setRating(book.getRating());
        bookFx.setIsbn(book.getIsbn());
        bookFx.setAmounrt(book.getAmount());
        bookFx.setReleaseDate(Utils.convertToLocalDate(book.getReleaseDate()));
        bookFx.setAuthorFxObjectProperty(AuthorConverter
                .convertFromAuthorToAuthorFx(book.getAuthor()));
        bookFx.setCategoryFxObjectProperty(CategoryConverter
                .convertFromCategoryToCategoryFx(book.getCategory()));
        return bookFx;

    }


}
