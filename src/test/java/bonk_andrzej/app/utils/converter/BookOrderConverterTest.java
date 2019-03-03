package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelDb.*;
import bonk_andrzej.app.fx.view.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookOrderConverterTest extends AbstractTest {

    @Mock
    ReaderConverter readerConverter;
    @Mock
    BookConverter bookConverter;
    @InjectMocks
    private BooksOrderConverter booksOrderConverter = new BooksOrderConverter();

    @Test
    void shouldReturnSameBookOrderLikeBookOrderFx() {
        //given
        BookOrder expectedBookOrder = makeBookOrder();
        BookOrdersFx bookOrdersFx = makeBookOrderFx();
        //when
        BookOrder bookOrderToCheck = booksOrderConverter.convertBookOrdersFxToBookOrders(bookOrdersFx);
        //then
        assertThat(expectedBookOrder).isEqualToIgnoringNullFields(bookOrderToCheck);
    }

    @Test
    void shouldReturnSameBookOrderFxLikeBookOrder() {
        //given
        BookOrder bookOrder = makeBookOrder();
        bookOrder.setBook(makeBook());
        BookFx bookFx = makeBookFx();
        BookOrdersFx expectedBookOrderFx = makeBookOrderFx();
        expectedBookOrderFx.setAmountBooksToReturn(5);
        expectedBookOrderFx.setAmountAllReturnedBooks(0);
        when(bookConverter.convertBookToBookFx(any(Book.class))).thenReturn(bookFx);
        //when
        BookOrdersFx bookOrdersFxToCheck = booksOrderConverter.convertBookOrdersToBookOrdersFx(bookOrder);
        //then
        assertThat(bookOrdersFxToCheck).isEqualToComparingOnlyGivenFields(expectedBookOrderFx);

    }

    private BookOrdersFx makeBookOrderFx() {
        return super.createBookOrderFx();
    }


    private BookOrder makeBookOrder() {
        return super.createBookOrder();
    }


    private Book makeBook() {
        return super.createBook();
    }

    private BookFx makeBookFx() {
        return super.createBookFx();

    }
}
