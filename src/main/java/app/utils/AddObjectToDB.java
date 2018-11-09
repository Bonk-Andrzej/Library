package app.utils;

import app.db.dao.CrudFacade;
import app.db.modelsDb.Author;
import app.db.modelsDb.Book;
import app.db.modelsDb.Category;
import app.utils.exceptions.ApplicationException;

import java.util.Date;


public class AddObjectToDB {
    public static void addObjectToDB() throws ApplicationException {
        CrudFacade crudFacade = new CrudFacade();


        Category category1 = new Category();
        category1.setName("Dramat");
        Category category2 = new Category();
        category2.setName("Sensacja");
        Category category3 = new Category();
        category3.setName("Reportaż");
        Category category4 = new Category();
        category4.setName("Fantastyka");



        Author author1 = new Author();
        author1.setName("Jakub");
        author1.setSurname("Ćwiek");
        Author author2 = new Author();
        author2.setName("Jarosław");
        author2.setSurname("Grzędowicz");
        Author author3 = new Author();
        author3.setName("Andrzej");
        author3.setSurname("Sapkowski");
        Author author4 = new Author();
        author4.setName("Marcin ");
        author4.setSurname("Przybyłek");



        Book book1 = new Book();
        book1.setCategory(category1);
        book1.setAuthor(author1);
        book1.setTitle("Kłamca");
        book1.setIsbn("2222");
        book1.setRating(4);
        book1.setReleaseDate(new Date());
        book1.setAddedDate(new Date());
        book1.setDescription("Super mega hiper fullwypas ksiazka");

        Book book2 = new Book();
        book2.setCategory(category3);
        book2.setAuthor(author2);
        book2.setTitle("Pan Lodowego Ogrodu");
        book2.setIsbn("5555");
        book2.setRating(5);
        book2.setReleaseDate(new Date());
        book2.setAddedDate(new Date());
        book2.setDescription("Super mega hiper fullwypas ksiazka");

        Book book3 = new Book();
        book3.setCategory(category4);
        book3.setAuthor(author3);
        book3.setTitle("Pani Jeziorna");
        book3.setIsbn("2222");
        book3.setRating(5);
        book3.setReleaseDate(new Date());
        book3.setAddedDate(new Date());
        book3.setDescription("Super mega hiper fullwypas ksiazka");

        Book book4 = new Book();
        book4.setCategory(category4);
        book4.setAuthor(author4);
        book4.setTitle("Gamedec Zabaweczki");
        book4.setIsbn("2222");
        book4.setRating(3);
        book4.setReleaseDate(new Date());
        book4.setAddedDate(new Date());
        book4.setDescription("Super mega hiper fullwypas ksiazka");

        try {
            crudFacade.create(book1);
            crudFacade.create(book2);
            crudFacade.create(book3);
            crudFacade.create(book4);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
