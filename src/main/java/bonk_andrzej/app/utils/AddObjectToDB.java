package bonk_andrzej.app.utils;

import bonk_andrzej.app.db.dao.GenericCrud;
import bonk_andrzej.app.db.modelsDb.Author;
import bonk_andrzej.app.db.modelsDb.Category;
import bonk_andrzej.app.db.modelsDb.Reader;
import bonk_andrzej.app.utils.exceptions.ApplicationException;

import java.util.Arrays;
import java.util.List;


public class AddObjectToDB {

    private AddObjectToDB() {
    }

    public static void addObjectToDB() {
        GenericCrud genericCrud = new GenericCrud();

        Category category1 = new Category("Dramat");
        Category category2 = new Category("Sensacja");
        Category category3 = new Category("Reportaż");
        Category category4 = new Category("Fantastyka");

        Author author1 = new Author("Jakub", "Ćwiek");
        Author author2 = new Author("Jarosław", "Grzędowicz");
        Author author3 = new Author("Andrzej", "Sapkowski");
        Author author4 = new Author("Marcin", "Przybyłek");

        Reader reader1 = new Reader("Andrzej", "Bonk");
        Reader reader2 = new Reader("Grzegorz ", "Bżęczyszczykiewicz");
        Reader reader3 = new Reader("Krzysztof", "Krafczyk");
        Reader reader4 = new Reader("Tadeusz", "Norek");

        try {
            List<Category> categoriesToAdd = Arrays.asList(category1, category2, category3, category4);
            List<Author> authorsToAdd = Arrays.asList(author1, author2, author3, author4);
            List<Reader> readersToAdd = Arrays.asList(reader1, reader2, reader3, reader4);

            List<Category> categoriesInDB = genericCrud.getAll(Category.class);
            List<Author> authorsInDB = genericCrud.getAll(Author.class);
            List<Reader> readersInDB = genericCrud.getAll(Reader.class);

            for (Category categoryToAdd : categoriesToAdd) {
                if (categoriesInDB.stream().noneMatch(categoryInDb -> categoryInDb.getName().equalsIgnoreCase(categoryToAdd.getName()))) {
                    genericCrud.createOrUpdate(categoryToAdd);
                }
            }

            for (Author authorToAdd : authorsToAdd) {
                if (authorsInDB.stream().noneMatch(authorInDb -> authorInDb.getName().equalsIgnoreCase(authorToAdd.getName())
                        && authorInDb.getSurname().equalsIgnoreCase(authorToAdd.getSurname()))) {
                    genericCrud.createOrUpdate(authorToAdd);
                }
            }

            for (Reader readerToAdd : readersToAdd) {
                if (readersInDB.stream().noneMatch(readerInDB -> readerInDB.getName().equalsIgnoreCase(readerToAdd.getName())
                        && readerInDB.getSurname().equalsIgnoreCase(readerToAdd.getSurname()))) {
                    genericCrud.createOrUpdate(readerToAdd);
                }
            }

        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }
}
