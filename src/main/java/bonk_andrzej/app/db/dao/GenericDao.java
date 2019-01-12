package bonk_andrzej.app.db.dao;

import bonk_andrzej.app.utils.exceptions.ApplicationException;

import java.util.List;

public interface GenericDao<T, I> {

    T createOrUpdate(T entity) throws ApplicationException;
    T getById(Class<T> classType, I id) throws ApplicationException;
    void delete(T entity) throws ApplicationException;
    List<T> getAll(Class<T> classType) throws ApplicationException;
    void deleteById(Class<T> classType, I id) throws ApplicationException;

}

