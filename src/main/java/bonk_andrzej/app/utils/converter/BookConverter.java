package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelsDb.Book;
import bonk_andrzej.app.fx.view.BookFx;

public class BookConverter {
    private CategoryConverter categoryConverter = new CategoryConverter();
    private AuthorConverter authorConverter = new AuthorConverter();

    public Book convertBookFxToBook(BookFx bookFx) {
        Book book = new Book();
        book.setId(bookFx.getId());
        book.setTitle(bookFx.getTitle());
        book.setDescription(bookFx.getDescription());
        book.setRating(bookFx.getRating());
        book.setIsbn(bookFx.getIsbn());
        book.setLeftBooksForRent(Integer.valueOf(bookFx.getLeftBooksForRent()));
        book.setReleaseDate(bookFx.getReleaseDate());
        book.setCategory(categoryConverter.convertCategoryFxToCategory(bookFx.getCategoryFx()));
        book.setAuthor(authorConverter.convertAuthorFxToAuthor(bookFx.getAuthorFx()));
        return book;
    }

    public BookFx convertBookToBookFx(Book book) {
        BookFx bookFx = new BookFx();
        bookFx.setId(book.getId());
        bookFx.setTitle(book.getTitle());
        bookFx.setDescription(book.getDescription());
        bookFx.setRating(book.getRating());
        bookFx.setIsbn(book.getIsbn());
        bookFx.setLeftBooksForRent(String.valueOf(book.getLeftBooksForRent()));
        bookFx.setReleaseDate(book.getReleaseDate());
        bookFx.setCategoryFx(categoryConverter.convertCategoryToCategoryFx(book.getCategory()));
        bookFx.setAuthorFx(authorConverter.convertAuthorToAuthorFx(book.getAuthor()));
        return bookFx;
    }


}
