package bonk_andrzej.app.db.modelsDb;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

//@NoArgsConstructor
//@Getter
//@Setter
@Data
@NoArgsConstructor
@Entity
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(name, author.name) &&
                Objects.equals(surname, author.surname) &&
                Objects.equals(bookListForAuthor, author.bookListForAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, bookListForAuthor);
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }


}
