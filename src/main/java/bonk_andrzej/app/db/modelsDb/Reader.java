package bonk_andrzej.app.db.modelsDb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reader_id")
    private long id;
    @Column(name = "reader_name")
    private String name;
    @Column(name = "reader_surname")
    private String surname;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> bookList;

    @OneToMany( mappedBy = "reader", cascade = CascadeType.ALL)
    private List<BookOrder> bookOrderList;

    public Reader(String name, String surname, List<Book> bookList) {
        this.name = name;
        this.surname = surname;
        this.bookList = bookList;

    }

}
