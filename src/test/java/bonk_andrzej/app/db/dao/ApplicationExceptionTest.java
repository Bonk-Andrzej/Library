package bonk_andrzej.app.db.dao;

import bonk_andrzej.app.db.modelsDb.Author;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationExceptionTest {

    @Mock
    EntityManagerFactory entityManagerFactory;
    @Mock
    EntityManager entityManager;

    @InjectMocks
    GenericCrud genericCrud = new GenericCrud();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldThrowApplicationExceptionWhenAddEntity() throws ApplicationException {
        Author authorToAdd = new Author();
        authorToAdd.setId(1);
        authorToAdd.setName("Andrzej");
        authorToAdd.setSurname("Bonk");
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenThrow(new HibernateException("error"));
        try {
            genericCrud.createOrUpdate(authorToAdd);
            Assert.fail("exception");
        } catch (ApplicationException e) {
            assertEquals("Problem with create or update data in database.", e.getMessage());
        }
    }

    @Test
    void shouldThrowApplicationExceptionWhenTryGetEntityById() {
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenThrow(new HibernateException("error"));
        try {
            genericCrud.getById(Author.class, 1);
            Assert.fail("exception");
        } catch (ApplicationException e) {
            assertEquals("Item not found in database.", e.getMessage());
        }

    }

    @Test
    void shouldThrowApplicationExceptionWhenTryGetAllEntities() {
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenThrow(new HibernateException("error"));
        try {
            genericCrud.getAll(Author.class);
            Assert.fail("exception");
        } catch (ApplicationException e) {
            assertEquals("Problem with retrieves the data from the database.", e.getMessage());
        }
    }

    @Test
    void shouldThrowApplicationExceptionWhenTryDeleteEntity() {
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenThrow(new HibernateException("error"));
        try {
            genericCrud.delete(Author.class);
            Assert.fail("exception");
        } catch (ApplicationException e) {
            assertEquals("Problem with delete item.", e.getMessage());
        }
    }
}