package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelsDb.Reader;
import bonk_andrzej.app.fx.view.ReaderFx;

public class ReaderConverter {

    public Reader convertReaderFxToReader(ReaderFx readerFx) {
        Reader reader = new Reader();
        reader.setId(readerFx.getId());
        reader.setName(readerFx.getName());
        reader.setSurname(readerFx.getSurname());
        return reader;
    }

    public ReaderFx convertReaderToReaderFx(Reader reader) {
        ReaderFx readerFx = new ReaderFx();
        readerFx.setId(reader.getId());
        readerFx.setName(reader.getName());
        readerFx.setSurname(reader.getSurname());
        return readerFx;
    }
}
