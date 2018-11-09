package app.db.modelsDb;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity(name = "lender_books")
@Table
public class LenderBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lender_id")
    private long id;
    @Column(name = "lender_name")
    private String name;
    @Column(name = "lender_surname")
    private String surname;
    @Column
    private LocalDate lenderDate;
    @Column
    private LocalDate returnDate;
    @Column
    private LocalDate actualDateOfReturn;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Book> bookList;


    public LenderBooks() {
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

    public LocalDate getLenderDate() {
        return lenderDate;
    }

    public void setLenderDate(LocalDate lenderDate) {
        this.lenderDate = lenderDate;
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
}
