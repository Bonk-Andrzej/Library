package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelsDb.Reader;
import bonk_andrzej.app.fx.view.ReaderFx;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReaderConverterTest {
    private final ReaderConverter readerConverter = new ReaderConverter();
    private final long idForConvert = 1L;
    private final String nameForConvert = "Andrzej";
    private final String surnameForConvert = "Bonk";

    @Test
    void shouldReturnReaderWithTheSameFieldsLikeReaderFx() {
        //given
        Reader expectedReader = setReaderFields();
        ReaderFx readerFx = setReaderFxFields();
        //when
        Reader readerAfterConvert = readerConverter.convertReaderFxToReader(readerFx);
        //then
        assertThat(expectedReader).isEqualTo(readerAfterConvert);
    }

    @Test
    void shouldReturnReaderFxWithTheSameFieldsLikeReader() {
        //given
        ReaderFx expectedReaderFx = setReaderFxFields();
        Reader reader = setReaderFields();
        //when
        ReaderFx readerFxAfterConvert = readerConverter.convertReaderToReaderFx(reader);
        //then
        assertThat(expectedReaderFx).isEqualToComparingFieldByField(readerFxAfterConvert);
    }

    private Reader setReaderFields() {
        Reader reader = new Reader();
        reader.setId(idForConvert);
        reader.setName(nameForConvert);
        reader.setSurname(surnameForConvert);
        return reader;
    }

    private ReaderFx setReaderFxFields() {
        ReaderFx readerFx = new ReaderFx();
        readerFx.setId(idForConvert);
        readerFx.setName(nameForConvert);
        readerFx.setSurname(surnameForConvert);
        return readerFx;
    }

}