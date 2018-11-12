package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelsDb.Book;
import bonk_andrzej.app.fx.view.BookFx;

public class BookConverter {

    public static Book convertFromBookFxToBook(BookFx bookFx) {
        Book book = new Book();
        book.setId(bookFx.getId());
        book.setTitle(bookFx.getTitle());
        book.setDescription(bookFx.getDescription());
        book.setRating(bookFx.getRating());
        book.setIsbn(bookFx.getIsbn());
        book.setAmount(Integer.valueOf(bookFx.getAmounrt()));
        book.setReleaseDate(bookFx.getReleaseDate());
        book.setAddedDate((bookFx.getAddedDate()));
        book.setCategory(CategoryConverter.convertFromCategoryFxToCategory(bookFx.getCategoryFx()));
        book.setAuthor(AuthorConverter.convertFromAuthorFxToAuthor(bookFx.getAuthorFx()));
        return book;
    }

    public static BookFx convertFromBookToBookFx(Book book) {
        BookFx bookFx = new BookFx();
        bookFx.setId(book.getId());
        bookFx.setTitle(book.getTitle());
        bookFx.setDescription(book.getDescription());
        bookFx.setRating(book.getRating());
        bookFx.setIsbn(book.getIsbn());
        bookFx.setAmounrt(String.valueOf(book.getAmount()));
        bookFx.setReleaseDate(book.getReleaseDate());
        bookFx.setCategoryFx(CategoryConverter.convertFromCategoryToCategoryFx(book.getCategory()));
        bookFx.setAuthorFx(AuthorConverter.convertFromAuthorToAuthorFx(book.getAuthor()));
        return bookFx;

    }


}
