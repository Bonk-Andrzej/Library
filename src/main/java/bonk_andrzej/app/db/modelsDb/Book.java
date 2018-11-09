package bonk_andrzej.app.db.modelsDb;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String title;
    @Column
    private String description;
    @Column
    private int rating;
    @Column
    private String isbn;
    @Column
    private String amount;
    @Column
    private LocalDate releaseDate;
    @Column
    private LocalDate addedDate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "bookList", cascade = CascadeType.ALL)
    private List<LenderBooks> lenderBooksList;

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long book_id) {
        this.id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String book_title) {
        this.title = book_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String book_description) {
        this.description = book_description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int book_rating) {
        this.rating = book_rating;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<LenderBooks> getLenderBooksList() {
        return lenderBooksList;
    }

    public void setLenderBooksList(List<LenderBooks> lenderBooksList) {
        this.lenderBooksList = lenderBooksList;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }
}

