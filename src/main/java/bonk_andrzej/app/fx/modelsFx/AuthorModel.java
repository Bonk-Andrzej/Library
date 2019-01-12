package bonk_andrzej.app.fx.modelsFx;

import bonk_andrzej.app.db.dao.CrudFacade;
import bonk_andrzej.app.db.modelsDb.Author;
import bonk_andrzej.app.fx.view.AuthorFx;
import bonk_andrzej.app.utils.converter.AuthorConverter;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class AuthorModel {

    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>(new AuthorFx());

    private CrudFacade crudFacade = new CrudFacade();
    private AuthorConverter authorConverter = new AuthorConverter();


    public Author saveOrUpdateAuthorInDb() throws ApplicationException {
        Author author = authorConverter.convertAuthorFxToAuthor(getAuthorFxObjectProperty());
        crudFacade.createOrUpdate(author);
        initializeAuthorFromDb();
        return author;
    }

    public void deleteAuthorInDB() throws ApplicationException {
        Author authorToDelete = (Author) crudFacade.getById(
                Author.class, getAuthorFxObjectProperty().getId());
        crudFacade.delete(authorToDelete);
        initializeAuthorFromDb();
    }

    public void initializeAuthorFromDb() throws ApplicationException {
        List<Author> authorList = crudFacade.getAll(Author.class);
        authorFxObservableList.clear();
        authorList.forEach(author -> {
            AuthorFx authorFx = authorConverter.convertAuthorToAuthorFx(author);
            authorFxObservableList.add(authorFx);
        });
    }

    public AuthorFx getAuthorFxObjectProperty() {
        return authorFxObjectProperty.get();
    }

    public ObjectProperty<AuthorFx> authorFxObjectPropertyProperty() {
        return authorFxObjectProperty;
    }

    public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {
        this.authorFxObjectProperty.set(authorFxObjectProperty);
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }

}
