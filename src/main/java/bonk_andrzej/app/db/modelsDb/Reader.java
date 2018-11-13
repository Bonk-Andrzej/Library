package bonk_andrzej.app.db.modelsDb;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "lender_books")
@Table
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lender_id")
    private long id;
    @Column(name = "lender_name")
    private String name;
    @Column(name = "lender_surname")
    private String surname;
    @Column
    private LocalDate rentalDate;
    @Column
    private LocalDate returnDate;
    @Column
    private LocalDate actualDateOfReturn;
    @Column
    private long amountBorrowedBook;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Book> bookList;


    public Reader() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String lender_name) {
        this.name = lender_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String lender_surname) {
        this.surname = lender_surname;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate lenderDate) {
        this.rentalDate = lenderDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getActualDateOfReturn() {
        return actualDateOfReturn;
    }

    public void setActualDateOfReturn(LocalDate actualDateOfReturn) {
        this.actualDateOfReturn = actualDateOfReturn;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public long getAmountBorrowedBook() {
        return amountBorrowedBook;
    }

    public void setAmountBorrowedBook(long amountBooksRent) {
        this.amountBorrowedBook = amountBooksRent;
    }
}
