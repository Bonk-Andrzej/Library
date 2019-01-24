package bonk_andrzej.app.db.modelsDb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
//@Data
@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private int rating;
    private String isbn;
    private Integer leftBooksForRent;
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER ,orphanRemoval = true)
    private List<BookOrder> bookOrderList;

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
                Objects.equals(category, book.category) &&
                Objects.equals(author, book.author) &&
                Objects.equals(bookOrderList, book.bookOrderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, rating, isbn, leftBooksForRent, releaseDate, category, author, bookOrderList);
    }
}

