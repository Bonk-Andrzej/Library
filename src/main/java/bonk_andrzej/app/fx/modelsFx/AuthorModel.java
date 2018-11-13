package bonk_andrzej.app.fx.modelsFx;

import bonk_andrzej.app.db.dao.CrudFacade;
import bonk_andrzej.app.db.modelsDb.Author;
import bonk_andrzej.app.utils.converter.AuthorConverter;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import bonk_andrzej.app.fx.view.AuthorFx;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class AuthorModel {

    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>(new AuthorFx());

    private CrudFacade crudFacade = new CrudFacade();

    public void saveAuthorInDb() throws ApplicationException {
        Author author = AuthorConverter.convertFromAuthorFxToAuthor(getAuthorFxObjectProperty());
        crudFacade.create(author);
        initializeAuthorFromDb();
    }

    public void updateAuthorInDb() throws ApplicationException {
        Author authorToUpdate = AuthorConverter.convertFromAuthorFxToAuthor(getAuthorFxObjectProperty());
        crudFacade.update(authorToUpdate);
        initializeAuthorFromDb();
    }

    public void deleteAuthorInDB() throws ApplicationException {
        Author authorTodelete = (Author) crudFacade.getById(
                Author.class, getAuthorFxObjectProperty().getId());
        crudFacade.deleteO(authorTodelete);
        initializeAuthorFromDb();
    }

    public void initializeAuthorFromDb() throws ApplicationException {
        List<Author> authorList = crudFacade.getAll(Author.class);
        authorFxObservableList.clear();
        authorList.forEach(author -> {
            AuthorFx authorFx = AuthorConverter.convertFromAuthorToAuthorFx(author);
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