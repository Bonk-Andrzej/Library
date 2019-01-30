package bonk_andrzej.app.db.modelsDb;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Data
@Entity
public class BookOrder extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer amountAllBorrowedBooks;
    private Integer amountBooksToReturn;
    private Integer amountReturnedBooksNow;
    private Integer allReturnedBooks;
    private LocalDate lenderDate;
    private LocalDate returnDate;
    private LocalDate actualDateOfReturn;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


}
