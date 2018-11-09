package app.db.modelsDb;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"category_name"})})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;

    @Column(name = "category_name", unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Book> bookListForCategory;

    public Category() {
    }

    public long getId() {
        return id;
    }

    public void setId(long category_id) {
        this.id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookListForCategory() {
        return bookListForCategory;
    }

    public void setBookListForCategory(List<Book> bookListForCategory) {
        this.bookListForCategory = bookListForCategory;
    }
}
