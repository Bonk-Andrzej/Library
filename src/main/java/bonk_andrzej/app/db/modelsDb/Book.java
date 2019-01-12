package bonk_andrzej.app.db.modelsDb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;
    @Column()
    private String title;
    @Column
    private String description;
    @Column
    private int rating;
    @Column
    private String isbn;
    @Column
    private Integer leftBooksForRent;
    @Column
    private LocalDate releaseDate;
    @Column
    private LocalDate addedDate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "orders_for_books",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")}
    )
    private List<BookOrder> bookOrderList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_reader",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "reader_id")}
    )
    private List<Reader> readerList;

    public Book(String title, String description, int rating, String isbn, Integer leftBooksForRent,
                LocalDate releaseDate, LocalDate addedDate, Author author, Category category) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.isbn = isbn;
        this.leftBooksForRent = leftBooksForRent;
        this.releaseDate = releaseDate;
        this.addedDate = addedDate;
        this.author = author;
        this.category = category;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                rating == book.rating &&
                Objects.equals(title, book.title) &&
                Objects.equals(description, book.description) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(leftBooksForRent, book.leftBooksForRent) &&
                Objects.equals(releaseDate, book.releaseDate) &&
                Objects.equals(addedDate, book.addedDate) &&
                Objects.equals(author, book.author) &&
                Objects.equals(category, book.category) &&
                Objects.equals(bookOrderList, book.bookOrderList) &&
                Objects.equals(readerList, book.readerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, rating, isbn, leftBooksForRent,
                releaseDate, addedDate, author, category, bookOrderList, readerList);
    }
}

