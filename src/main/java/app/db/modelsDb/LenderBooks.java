package app.db.modelsDb;

import javax.persistence.*;
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
    private Date lenderDate;
    @Column
    private Date returnDate;
    @Column
    private Date actualDateOfReturn;

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

    public Date getLenderDate() {
        return lenderDate;
    }

    public void setLenderDate(Date lender_date) {
        this.lenderDate = lender_date;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date return_date) {
        this.returnDate = return_date;
    }

    public Date getActualDateOfReturn() {
        return actualDateOfReturn;
    }

    public void setActualDateOfReturn(Date actual_date_of_return) {
        this.actualDateOfReturn = actual_date_of_return;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
