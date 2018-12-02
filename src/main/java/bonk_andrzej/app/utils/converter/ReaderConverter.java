package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelsDb.Book;
import bonk_andrzej.app.db.modelsDb.Reader;

import bonk_andrzej.app.fx.view.ReaderFx;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.util.List;

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
