package bonk_andrzej.app.db.modelsDb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;
    @Column(name = "category_name", unique = true, length = 20)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Book> bookListForCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(name, category.name) &&
                Objects.equals(bookListForCategory, category.bookListForCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bookListForCategory);
    }
}
