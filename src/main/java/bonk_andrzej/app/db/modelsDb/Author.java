package bonk_andrzej.app.db.modelsDb;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Data
@Entity
public class Author extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Book> books;


    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

}
