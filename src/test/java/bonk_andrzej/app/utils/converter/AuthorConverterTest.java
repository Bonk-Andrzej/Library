package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelsDb.Author;
import bonk_andrzej.app.fx.view.AuthorFx;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorConverterTest {

    private final AuthorConverter authorConverter = new AuthorConverter();
    private final long idForConvert = 1L;
    private final String nameForConvert = "Andrzej";
    private final String surnameForConvert = "Bonk";


    @Test
    public void shouldReturnAuthorWithTheSameFieldsLikeAuthorFx() {
        //given
        Author expectedAuthor = setAuthorFields();
        AuthorFx authorFx = setAuthorFxFields();
        //when
        Author authorAfterConvert = authorConverter.convertAuthorFxToAuthor(authorFx);
        //then
        assertThat(expectedAuthor).isEqualTo(authorAfterConvert);
    }

    @Test
    public void shouldReturnAuthorFxWithTheSameFieldsLikeAuthor() {
        //given
        AuthorFx expectedAuthorFx = setAuthorFxFields();
        Author author = setAuthorFields();
        //when
        AuthorFx authorFxAfterConvert = authorConverter.convertAuthorToAuthorFx(author);
        //then
        assertThat(authorFxAfterConvert).isEqualToComparingFieldByField(expectedAuthorFx);
    }

    private AuthorFx setAuthorFxFields() {
        AuthorFx authorFx = new AuthorFx();
        authorFx.setName(nameForConvert);
        authorFx.setSurname(surnameForConvert);
        authorFx.setId(idForConvert);
        return authorFx;
    }

    private Author setAuthorFields() {
        Author author = new Author();
        author.setName(nameForConvert);
        author.setSurname(surnameForConvert);
        author.setId(idForConvert);
        return author;
    }

}
