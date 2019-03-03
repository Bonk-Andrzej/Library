package bonk_andrzej.app.db.dao;

import bonk_andrzej.app.utils.exception.ApplicationException;

import java.util.List;

public interface GenericDao<T> {

    T createOrUpdate(T entity) throws ApplicationException;
    T getById(Class<T> classType, Long id) throws ApplicationException;
    void delete(T entity) throws ApplicationException;
    List<T> getAll(Class<T> classType) throws ApplicationException;

}

