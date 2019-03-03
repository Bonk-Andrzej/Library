package bonk_andrzej.app.db.dao;

import bonk_andrzej.app.db.modelDb.BaseModel;
import bonk_andrzej.app.utils.FxmlUtils;
import bonk_andrzej.app.utils.exception.ApplicationException;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;


public class GenericCrud<T extends BaseModel> implements GenericDao<T>, Serializable {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public T createOrUpdate(T entity) throws ApplicationException {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (HibernateException e) {
            e.getMessage();
            throw new ApplicationException(FxmlUtils.getBundleForApplicationErrors().getString("error.create.or.update"));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public T getById(Class<T> classType, Long id) throws ApplicationException {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            T object = classType.cast(entityManager.find(classType, id));
            entityManager.getTransaction().commit();
            return object;
        } catch (HibernateException e) {
            e.getMessage();
            throw new ApplicationException(FxmlUtils.getBundleForApplicationErrors().getString("error.not.found"));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<T> getAll(Class<T> classType) throws ApplicationException {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List genericList = entityManager.createQuery(
                    "from " + classType.getName()).getResultList();
            entityManager.getTransaction().commit();
            return genericList;
        } catch (HibernateException e) {
            e.getMessage();
            throw new ApplicationException(FxmlUtils.getBundleForApplicationErrors().getString("error.not.found.all"));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(T entity) throws ApplicationException {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            entityManager.getTransaction().commit();

        } catch (HibernateException e) {
            e.getMessage();
            throw new ApplicationException(FxmlUtils.getBundleForApplicationErrors().getString("error.delete"));
        } finally {
            entityManager.close();
        }
    }

}


