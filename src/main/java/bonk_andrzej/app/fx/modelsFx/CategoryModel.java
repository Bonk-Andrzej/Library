package bonk_andrzej.app.fx.modelsFx;

import bonk_andrzej.app.db.dao.GenericCrud;
import bonk_andrzej.app.db.modelsDb.Category;
import bonk_andrzej.app.fx.view.CategoryFx;
import bonk_andrzej.app.utils.converter.CategoryConverter;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.List;


public class CategoryModel {

    private ObjectProperty<CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>(new CategoryFx());
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();
    private TreeItem<String> treeItemRoot = new TreeItem<>();
    private GenericCrud genericCrud = new GenericCrud();

    private CategoryConverter categoryConverter = new CategoryConverter();


    public void deleteCategory() throws ApplicationException {
       Category categoryToDelete = (Category) genericCrud.getById(
                Category.class, getCategoryFxObjectProperty().getId());
        genericCrud.delete(categoryToDelete);
        initializeCategoryFromDB();
    }

    public void saveOrUpdateCategoryInDB() throws ApplicationException {
        Category categoryToUpdate = categoryConverter.convertCategoryFxToCategory(getCategoryFxObjectProperty());
        genericCrud.createOrUpdate(categoryToUpdate);
        initializeCategoryFromDB();
    }

    public void initializeCategoryFromDB() throws ApplicationException {
        List<Category> categories = genericCrud.getAll(Category.class);
        initializeCategoryFxList(categories);
        initializeItemRoot(categories);
    }

    private void initializeItemRoot(List<Category> categories) {
        treeItemRoot.getChildren().clear();
        categories.forEach(category -> {
            TreeItem<String> categoryItem = new TreeItem<>(category.getName());
            category.getBooks().forEach(
                    book -> categoryItem.getChildren()
                            .add(new TreeItem<>(book.getTitle())));
            treeItemRoot.getChildren().add(categoryItem);
        });
    }

    private void initializeCategoryFxList(List<Category> categories) {
        categoryFxObservableList.clear();
        categories.forEach(c -> {
            CategoryFx categoryFx = categoryConverter
                    .convertCategoryToCategoryFx(c);
            categoryFxObservableList.add(categoryFx);
        });
    }


    public CategoryFx getCategoryFxObjectProperty() {
        return categoryFxObjectProperty.get();
    }

    public ObjectProperty<CategoryFx> categoryFxObjectPropertyProperty() {
        return categoryFxObjectProperty;
    }

    public void setCategoryFxObjectProperty(CategoryFx categoryFxObjectProperty) {
        this.categoryFxObjectProperty.set(categoryFxObjectProperty);
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public TreeItem<String> getTreeItemRoot() {
        return treeItemRoot;
    }

    public void setTreeItemRoot(TreeItem<String> treeItemRoot) {
        this.treeItemRoot = treeItemRoot;
    }
}
