package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelsDb.Author;
import bonk_andrzej.app.db.modelsDb.Book;
import bonk_andrzej.app.db.modelsDb.Category;
import bonk_andrzej.app.fx.view.AuthorFx;
import bonk_andrzej.app.fx.view.BookFx;
import bonk_andrzej.app.fx.view.CategoryFx;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookConverterTest {

    private final long idForConvert = 1L;
    private final String titleForConvert = "Ogniem i mieczem";
    private final String descriptionForConvert = "Książka w ktorej walcza i na miecze i na ogień";
    private final int ratingForConvert = 5;
    private final String isbnForConvert = "ISBN-13: 978-83-900210-1-0";
    private final String amountLeftBooksForConvert = "5";
    private final LocalDate releaseDateForConvert = LocalDate.of(2010, 10, 10);

    private final long idCategoryForConvert = 1L;
    private final String nameCategoryForConvert = "fikcja historyczna";
    private final long idAuthorForConvert = 1L;
    private final String nameAuthorForConvert = "Henryk";
    private final String surnameAuthorForConvert = "Sienkiewicz";


    private CategoryConverter categoryConverter = new CategoryConverter();
    @Mock
    private AuthorConverter authorConverter = new AuthorConverter();

    @Mock
    private AuthorFx authorFx;
    @Mock
    private Author author;
    @Mock
    private CategoryFx categoryFx;
    @Mock
    private Category category;
    @Mock
    Book book;
    @Mock
    BookFx bookFx;

    @InjectMocks
    private final BookConverter bookConverter = new BookConverter();

    @Test
    void shouldReturnBookWithTheSameFieldsLikeBookFx() throws ApplicationException {
        //given
        Book expectedBook = prepareBookTestObject();
        BookFx bookFx = prepareBookFxTestObject();
        //when
        Book bookAfterConvert = bookConverter.convertBookFxToBook(bookFx);
        //then
        assertThat(expectedBook).isEqualTo(bookAfterConvert);
    }

    @Test
    void shouldReturnBookFxWithTheSameFieldsLikeBook() {
        //given
        BookFx expectedBookFx = prepareBookFxTestObject();
        Book book = prepareBookTestObject();
        //when
        BookFx bookFxAfterConvert = bookConverter.convertBookToBookFx(book);
        //then
        assertThat(expectedBookFx).isEqualToComparingFieldByField(bookFxAfterConvert);
    }

    private Book prepareBookTestObject() {
        Book book = new Book();
        book.setId(idForConvert);
        book.setTitle(titleForConvert);
        book.setDescription(descriptionForConvert);
        book.setRating(ratingForConvert);
        book.setIsbn(isbnForConvert);
        book.setLeftBooksForRent(Integer.valueOf(amountLeftBooksForConvert));
        book.setReleaseDate(releaseDateForConvert);

        Author author = new Author();
        author.setName(nameAuthorForConvert);
        author.setSurname(surnameAuthorForConvert);
        author.setId(idForConvert);
//        doCallRealMethod().when(book).setAuthor(any(Author.class));
        when(authorConverter.convertAuthorFxToAuthor(authorFx)).thenReturn(author);

        doCallRealMethod().when(book).setCategory(any(Category.class));
        return book;
    }

    private BookFx prepareBookFxTestObject() {
        BookFx bookFx = new BookFx();
        bookFx.setId(idForConvert);
        bookFx.setTitle(titleForConvert);
        bookFx.setDescription(descriptionForConvert);
        bookFx.setRating(ratingForConvert);
        bookFx.setIsbn(isbnForConvert);
        bookFx.setLeftBooksForRent(amountLeftBooksForConvert);
        bookFx.setReleaseDate(releaseDateForConvert);
        Category category = new Category();
        category.setId(1);
        category.setName("categoria1");
        bookFx.setCategoryFx(categoryConverter.convertCategoryToCategoryFx(category));
        //todo

//        doCallRealMethod().when(bookFx).setAuthorFx(any(AuthorFx.class));
//        doCallRealMethod().when(bookFx).setCategoryFx(any(CategoryFx.class));

        return bookFx;
    }
//    private Author setAuthorFields() {
//        Author author = new Author();
//        author.setName(nameAuthorForConvert);
//        author.setSurname(surnameAuthorForConvert);
//        author.setId(idForConvert);
//        return author;
//    }
}