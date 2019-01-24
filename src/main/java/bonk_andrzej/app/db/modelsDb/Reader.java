package bonk_andrzej.app.db.modelsDb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

//@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.REMOVE)
    private List<BookOrder> bookOrderList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return id == reader.id &&
                Objects.equals(name, reader.name) &&
                Objects.equals(surname, reader.surname) &&
                Objects.equals(bookOrderList, reader.bookOrderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, bookOrderList);
    }
}
