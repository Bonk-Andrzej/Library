package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelDb.Author;
import bonk_andrzej.app.db.modelDb.Book;
import bonk_andrzej.app.db.modelDb.Category;
import bonk_andrzej.app.fx.view.AuthorFx;
import bonk_andrzej.app.fx.view.BookFx;
import bonk_andrzej.app.fx.view.CategoryFx;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookConverterTest extends AbstractTest {

    @Mock
    private CategoryConverter categoryConverter;
    @Mock
    private AuthorConverter authorConverter;

    @InjectMocks
    private final BookConverter bookConverter = new BookConverter();

    @Test
    void shouldReturnBookWithTheSameFieldsLikeBookFx() {
        //given
        Category category = makeCategory();
        Author author = makeAuthor();
        when(categoryConverter.convertCategoryFxToCategory(any(CategoryFx.class))).thenReturn(category);
        when(authorConverter.convertAuthorFxToAuthor(any(AuthorFx.class))).thenReturn(author);
        Book expectedBook = makeBook();
        BookFx bookFx = makeBookFx();
        //when
        Book bookAfterConvert = bookConverter.convertBookFxToBook(bookFx);
        //then
        assertThat(bookAfterConvert.getId()).isEqualTo(expectedBook.getId());
        assertThat(bookAfterConvert.getAuthor().getName()).isEqualTo(expectedBook.getAuthor().getName());
        assertThat(bookAfterConvert.getCategory().getName()).isEqualTo(expectedBook.getCategory().getName());
    }

    @Test
    void shouldReturnBookFxWithTheSameFieldsLikeBook() {
        //given
        CategoryFx categoryFx = makeCategoryFx();
        AuthorFx authorFx = makeAuthorFx();
        when(categoryConverter.convertCategoryToCategoryFx(any(Category.class))).thenReturn(categoryFx);
        when(authorConverter.convertAuthorToAuthorFx(any(Author.class))).thenReturn(authorFx);
        BookFx expectedBookFx = makeBookFx();
        Book book = makeBook();
        //when
        BookFx bookFxAfterConvert = bookConverter.convertBookToBookFx(book);
        //then
        assertThat(bookFxAfterConvert.getId()).isEqualTo(expectedBookFx.getId());
        assertThat(bookFxAfterConvert.getAuthorFx().getName()).isEqualTo(expectedBookFx.getAuthorFx().getName());
        assertThat(bookFxAfterConvert.getCategoryFx().getName()).isEqualTo(expectedBookFx.getCategoryFx().getName());

    }

    private Book makeBook() {
        return super.createBook();
    }

    private BookFx makeBookFx() {
        return super.createBookFx();

    }

    private AuthorFx makeAuthorFx() {
        return super.createAuthorFx();
    }

    private Author makeAuthor() {
        return super.createAuthor();
    }

    private Category makeCategory() {
        return super.createCategory();
    }

    private CategoryFx makeCategoryFx() {
        return super.createCategoryFx();
    }
}