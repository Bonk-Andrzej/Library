package bonk_andrzej.app.db.modelsDb;

import javax.persistence.*;
import java.util.List;

@Entity(name = "author")
@Table
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "author_name")
    private String name;
    @Column(name = "author_surname")
    private String surname;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookListForAuthor;

    public Author() {
    }

    public long getId() {
        return id;
    }

    public void setId(long author_id) {
        this.id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String author_name) {
        this.name = author_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String author_surname) {
        this.surname = author_surname;
    }

    public List<Book> getBookListForAuthor() {
        return bookListForAuthor;
    }

    public void setBookListForAuthor(List<Book> bookListForAuthor) {
        this.bookListForAuthor = bookListForAuthor;
    }
}
