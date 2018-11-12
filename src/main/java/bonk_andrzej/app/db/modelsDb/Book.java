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
    private Integer amount;
    @Column
    private LocalDate releaseDate;
    @Column
    private LocalDate addedDate;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "bookList", cascade = CascadeType.ALL)
    private List<BorrowerBooks> borrowerBooksList;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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

    public List<BorrowerBooks> getBorrowerBooksList() {
        return borrowerBooksList;
    }

    public void setBorrowerBooksList(List<BorrowerBooks> borrowerBooksList) {
        this.borrowerBooksList = borrowerBooksList;
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

