package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelsDb.Reader;
import bonk_andrzej.app.fx.view.ReaderFx;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReaderConverterTest extends AbstractTest {
    private final ReaderConverter readerConverter = new ReaderConverter();

    @Test
    void shouldReturnReaderWithTheSameFieldsLikeReaderFx() {
        //given
        Reader expectedReader = makeReader();
        ReaderFx readerFx = makeReaderFx();
        //when
        Reader readerAfterConvert = readerConverter.convertReaderFxToReader(readerFx);
        //then
        assertThat(readerAfterConvert).isEqualTo(expectedReader);
    }

    @Test
    void shouldReturnReaderFxWithTheSameFieldsLikeReader() {
        //given
        ReaderFx expectedReaderFx = makeReaderFx();
        Reader reader = makeReader();
        //when
        ReaderFx readerFxAfterConvert = readerConverter.convertReaderToReaderFx(reader);
        //then
        assertThat(readerFxAfterConvert).isEqualToComparingFieldByField(expectedReaderFx);
    }

    private ReaderFx makeReaderFx() {
        return super.createReaderFx();
    }

    private Reader makeReader() {
        return super.createReader();
    }

}