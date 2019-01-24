package bonk_andrzej.app.fx.modelsFx;

import bonk_andrzej.app.db.dao.GenericCrud;
import bonk_andrzej.app.db.modelsDb.Author;
import bonk_andrzej.app.fx.view.AuthorFx;
import bonk_andrzej.app.utils.converter.AuthorConverter;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorModelTest {
    @Mock
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
    @Mock
    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>(new AuthorFx());
    @Mock
    private GenericCrud genericCrud = new GenericCrud();
    @Mock
    private AuthorConverter authorConverter = new AuthorConverter();

    @InjectMocks
    private AuthorModel authorModel = new AuthorModel();

    @Test
    void saveOrUpdateAuthorInDb() throws ApplicationException {
        //given
        AuthorFx authorFxToUpdate = new AuthorFx();
        authorFxToUpdate.setId(1);
        authorFxToUpdate.setName("Andrzej");
        authorFxToUpdate.setSurname("Bonk");
        authorFxObjectProperty.setValue(authorFxToUpdate);
        authorFxObservableList.add(authorFxObjectProperty.getValue());

        Author authorToUpdate = new Author();
        authorToUpdate.setId(1);
        authorToUpdate.setName("Andrzej");
        authorToUpdate.setSurname("Bonk");

        Author expectedAuthor = new Author();
        expectedAuthor.setId(1);
        expectedAuthor.setName("Adam");
        expectedAuthor.setSurname("Bonk");

        when(authorConverter.convertAuthorFxToAuthor(authorFxToUpdate)).thenReturn(expectedAuthor);
        when(genericCrud.createOrUpdate(authorToUpdate)).thenReturn(expectedAuthor);
        //when
        Author authorUpdated = authorModel.saveOrUpdateAuthorInDb();
        //then
        assertThat(expectedAuthor).isEqualTo(authorUpdated);
//czemu nie chce mi zmokowac crud facade po co wchodzi mimo wszystko do bazy danych
    }

    @Test
    void deleteAuthorInDB() {
    }

    @Test
    void initializeAuthorFromDb() {
    }
}