//package bonk_andrzej.app.utils;
//
//import bonk_andrzej.app.db.dao.CrudFacade;
//import bonk_andrzej.app.db.modelsDb.*;
//import bonk_andrzej.app.utils.exceptions.ApplicationException;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//
//public class AddObjectToDB {
//    public static void addObjectToDB() throws ApplicationException {
//        CrudFacade crudFacade = new CrudFacade();
//
//
//        Category category1 = new Category("Dramat");
//        Category category2 = new Category("Sensacja");
//        Category category3 = new Category("Reportaż");
//        Category category4 = new Category("Fantastyka");
//
//
//        Author author1 = new Author("Jakub", "Ćwiek");
//        Author author2 = new Author("Jarosław", "Grzędowicz");
//        Author author3 = new Author("Andrzej", "Sapkowski");
//        Author author4 = new Author("Marcin", "Przybyłek");
//
//        Book book1 = new Book("Kłamca", "Super mega hiper fullwypas ksiazka", 4, "2222", 10, 0, 10,
//                LocalDate.now(), LocalDate.now(), author1, category1);
//
//        Book book2 = new Book("Pan Lodowego Ogrodu", "Super mega hiper fullwypas ksiazka", 4, "5555",
//                10, 0, 10, LocalDate.of(1500, 10, 10), LocalDate.now(), author2, category2);
//
//        Book book3 = new Book("Pani Jeziorna", "Super mega hiper fullwypas ksiazka", 4, "2222",
//                10, 0, 10, LocalDate.of(1500, 10, 10), LocalDate.now(), author3, category3);
//
//        Book book4 = new Book("Gamedec Zabaweczki", "Super mega hiper fullwypas ksiazka", 4, "2222",
//                10, 0, 10, LocalDate.of(1500, 10, 10), LocalDate.now(), author4, category4);
//
//        Set<Book> bookList = new ArrayList<>();
//        bookList.add(book1);
//        bookList.add(book2);
//        bookList.add(book3);
//        bookList.add(book4);
//
//        Reader reader1 = new Reader("Andrzej", "Bonk",bookList);
//        Reader reader2 = new Reader("aaaa", "aaaa",bookList);
//        Reader reader3 = new Reader("wwwww", "eeeeee",bookList);
//        Reader reader4 = new Reader("zzzzz", "xxxxxx",bookList);
//
//        List<Reader> readerList = new ArrayList<>();
//        readerList.add(reader1);
//        readerList.add(reader2);
//        readerList.add(reader3);
//        readerList.add(reader4);
//
//        BookOrder bookOrder1 = new BookOrder(10, 10, 0,
//                LocalDate.now(), LocalDate.now(), LocalDate.now(),reader1,bookList);
//        BookOrder bookOrder2 = new BookOrder(10, 10, 0,
//                LocalDate.now(), LocalDate.now(), LocalDate.now(),reader2,bookList);
//        BookOrder bookOrder3 = new BookOrder(10, 10, 0,
//                LocalDate.now(), LocalDate.now(), LocalDate.now(),reader3,bookList);
//        BookOrder bookOrder4 = new BookOrder(10, 10, 0,
//                LocalDate.now(), LocalDate.now(), LocalDate.now(),reader4,bookList);
//
//        List<BookOrder> bookOrderList = new ArrayList<>();
//        bookOrderList.add(bookOrder1);
//        bookOrderList.add(bookOrder2);
//        bookOrderList.add(bookOrder3);
//        bookOrderList.add(bookOrder4);
//
//        try {
////            crudFacade.createOrUpdate(category1);
////            crudFacade.createOrUpdate(category2);
////            crudFacade.createOrUpdate(category3);
////            crudFacade.createOrUpdate(category4);
////
////            crudFacade.createOrUpdate(author1);
////            crudFacade.createOrUpdate(author2);
////            crudFacade.createOrUpdate(author3);
////            crudFacade.createOrUpdate(author4);
////
////            crudFacade.createOrUpdate(book1);
////            crudFacade.createOrUpdate(book2);
////            crudFacade.createOrUpdate(book3);
////            crudFacade.createOrUpdate(book4);
////
////            crudFacade.createOrUpdate(reader1);
////            crudFacade.createOrUpdate(reader2);
////            crudFacade.createOrUpdate(reader3);
////            crudFacade.createOrUpdate(reader4);
//
//            crudFacade.createOrUpdate(bookOrder1);
//            crudFacade.createOrUpdate(bookOrder2);
//            crudFacade.createOrUpdate(bookOrder3);
//            crudFacade.createOrUpdate(bookOrder4);
//
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//}
