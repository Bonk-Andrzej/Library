package bonk_andrzej.app.fx.view;

import javafx.beans.property.*;
import javafx.scene.control.TreeItem;

public class CategoryFx extends TreeItem<String> {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty();

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return name.get();
    }
}
