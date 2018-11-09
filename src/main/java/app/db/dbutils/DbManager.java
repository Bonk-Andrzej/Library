package app.db.dbutils;

import app.utils.FxmlUtils;
import app.utils.exceptions.ApplicationException;
import org.hibernate.HibernateException;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbManager {
    private static final Logger LOGGER = LoggerFactory.logger(DbManager.class);

    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager tryGetEntityConnection() throws ApplicationException {
        try {
            if (entityManagerFactory == null || entityManager == null) {
                entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
                entityManager = entityManagerFactory.createEntityManager();
            }
            return entityManager;
        } catch (HibernateException he) {
            he.printStackTrace();
            LOGGER.warn(he.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        }
    }

    public static EntityManagerFactory closeEntityConnection() throws ApplicationException {
        try {
            if (DbManager.tryGetEntityConnection() != null) {
                entityManager.close();
                entityManagerFactory.close();
            }
            return entityManagerFactory;
        } catch (HibernateException he) {
            he.printStackTrace();
            LOGGER.warn(he.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        }
    }


}

