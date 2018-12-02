package bonk_andrzej.app.db.modelsDb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;
    @Column
    private Integer amountAllBorrowedBooks;
    @Column
    private Integer amountBooksToReturn;
    @Column
    private Integer amountReturnedBooksNow;
    @Column
    private Integer allReturnedBooks;
    @Column
    private LocalDate lenderDate;
    @Column
    private LocalDate returnDate;
    @Column
    private LocalDate actualDateOfReturn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private List<Book> bookList;


    public BookOrder(Integer amountAllBorrowedBooks, Integer amountBooksToReturn, Integer amountReturnedBooksNow,
                     LocalDate lenderDate, LocalDate returnDate, LocalDate actualDateOfReturn, Reader reader, List<Book> bookList) {
        this.amountAllBorrowedBooks = amountAllBorrowedBooks;
        this.amountBooksToReturn = amountBooksToReturn;
        this.amountReturnedBooksNow = amountReturnedBooksNow;
        this.lenderDate = lenderDate;
        this.returnDate = returnDate;
        this.actualDateOfReturn = actualDateOfReturn;
        this.reader = reader;
        this.bookList = bookList;
    }


}
