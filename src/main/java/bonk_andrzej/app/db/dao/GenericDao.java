package bonk_andrzej.app.db.dao;

import bonk_andrzej.app.utils.exceptions.ApplicationException;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import java.util.List;

public interface GenericDao<T, I> {

    T createOrUpdate(T entity) throws EntityExistsException, IllegalStateException,
            IllegalArgumentException, TransactionRequiredException, ApplicationException;

    T getById(Class<T> classType, I id) throws IllegalStateException,
            IllegalArgumentException, ApplicationException;

    void delete(T entity) throws IllegalStateException, IllegalArgumentException,
            PersistenceException, ApplicationException;

    List<T> getAll(Class<T> classType) throws ApplicationException;

    void deleteById(Class<T> classType, I id) throws ApplicationException;

}

