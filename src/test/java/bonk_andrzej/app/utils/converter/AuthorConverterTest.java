package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelDb.Author;
import bonk_andrzej.app.fx.view.AuthorFx;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorConverterTest extends AbstractTest {

    private final AuthorConverter authorConverter = new AuthorConverter();



    @Test
    void shouldReturnAuthorWithTheSameFieldsLikeAuthorFx() {
        //given
        Author expectedAuthor = makeAuthor();
        AuthorFx authorFx = makeAuthorFx();
        //when
        Author authorAfterConvert = authorConverter.convertAuthorFxToAuthor(authorFx);
        //then
        assertThat(authorAfterConvert).isEqualTo(expectedAuthor);
    }

    @Test
    void shouldReturnAuthorFxWithTheSameFieldsLikeAuthor() {
        //given
        AuthorFx expectedAuthorFx = makeAuthorFx();
        Author author = makeAuthor();
        //when
        AuthorFx authorFxAfterConvert = authorConverter.convertAuthorToAuthorFx(author);
        //then
        assertThat(authorFxAfterConvert).isEqualToComparingFieldByField(expectedAuthorFx);
    }


    private AuthorFx makeAuthorFx(){
       return super.createAuthorFx();
    }
    private Author makeAuthor(){
        return super.createAuthor();
    }




}
