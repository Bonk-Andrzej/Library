package bonk_andrzej.app.db.modelDb;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
public class Reader extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.REMOVE)
    private List<BookOrder> bookOrders;

    public Reader(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
