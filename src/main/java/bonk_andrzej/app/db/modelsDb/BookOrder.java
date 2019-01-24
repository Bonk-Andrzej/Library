package bonk_andrzej.app.db.modelsDb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

//@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BookOrder {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookOrder bookOrder = (BookOrder) o;
        return id == bookOrder.id &&
                Objects.equals(amountAllBorrowedBooks, bookOrder.amountAllBorrowedBooks) &&
                Objects.equals(amountBooksToReturn, bookOrder.amountBooksToReturn) &&
                Objects.equals(amountReturnedBooksNow, bookOrder.amountReturnedBooksNow) &&
                Objects.equals(allReturnedBooks, bookOrder.allReturnedBooks) &&
                Objects.equals(lenderDate, bookOrder.lenderDate) &&
                Objects.equals(returnDate, bookOrder.returnDate) &&
                Objects.equals(actualDateOfReturn, bookOrder.actualDateOfReturn) &&
                Objects.equals(reader, bookOrder.reader) &&
                Objects.equals(book, bookOrder.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amountAllBorrowedBooks, amountBooksToReturn, amountReturnedBooksNow, allReturnedBooks,
                lenderDate, returnDate, actualDateOfReturn, reader, book);
    }
}
