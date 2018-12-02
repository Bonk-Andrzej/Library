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


    @ManyToOne( cascade = CascadeType.ALL)
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

    public Book(String title, String description, int rating, String isbn, Integer leftBooksForRent, Integer rentBooks,
                Integer leftBooksJuzNieWazne, LocalDate releaseDate, LocalDate addedDate, Author author, Category category) {
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


}

