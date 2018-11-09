package app.db.dao;

import app.utils.exceptions.ApplicationException;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import java.util.List;

public interface GenericDao<T, I> {

    T create(T entity) throws EntityExistsException, IllegalStateException,
            IllegalArgumentException, TransactionRequiredException, ApplicationException;

    T getById(Class<T> classType, I id) throws IllegalStateException,
            IllegalArgumentException, ApplicationException;

    void update(T entity) throws IllegalStateException,
            IllegalArgumentException, TransactionRequiredException, ApplicationException;

    void deleteO(T entity) throws IllegalStateException,
            IllegalArgumentException, TransactionRequiredException,
            PersistenceException, ApplicationException;

    List<T> getAll(Class<T> classType) throws ApplicationException;

    void deleteById(Class<T> classType, I id) throws ApplicationException;

}

