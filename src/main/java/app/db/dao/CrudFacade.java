package app.db.dao;


import app.utils.FxmlUtils;
import app.utils.exceptions.ApplicationException;
import org.hibernate.HibernateException;


import javax.persistence.*;

import java.util.List;

public class CrudFacade<T, I> implements GenericDao<T, I> {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    EntityManager entityManager;

    @Override
    public T create(T entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException, TransactionRequiredException, ApplicationException {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.create"));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public T getById(Class<T> classType, I id) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, EntityExistsException, ApplicationException {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            T object = classType.cast(entityManager.find(classType, id));
            entityManager.getTransaction().commit();

            return object;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.not.found"));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<T> getAll(Class<T> classType) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException, ApplicationException {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<T> genericList = entityManager.createQuery(
                    "from " + classType.getName()).getResultList();
            entityManager.getTransaction().commit();
            return genericList;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.not.found.all"));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, ApplicationException {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.update"));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteO(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException, ApplicationException {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));//TODO CO TO??
            entityManager.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.delete"));
        } finally {
            entityManager.close();
        }
    }


    @Override
    public void deleteById(Class<T> classType, I id) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException, ApplicationException {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            T object = classType.cast(entityManager.find(classType.getClass(), id));
            entityManager.remove(object);
            entityManager.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.delete"));
        } finally {
            entityManager.close();
        }
    }


}


