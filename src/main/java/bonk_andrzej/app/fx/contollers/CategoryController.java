package bonk_andrzej.app.fx.contollers;

import bonk_andrzej.app.fx.view.BookFx;
import bonk_andrzej.app.fx.view.CategoryFx;
import bonk_andrzej.app.fx.modelsFx.CategoryModel;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CategoryController extends TextField {

    @FXML
    public MenuItem deleteContectMenu;
    @FXML
    private Label text;
    @FXML
    private TextField categoryTextField;
    @FXML
    private Button addCategoryButton;
    @FXML
    private Button editCategoryButton;
    @FXML
    private Button deleteCategoryButton;
    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    @FXML
    private TreeView<String> categoryTreeView;

    private CategoryModel categoryModel;

    @FXML
    public void initialize() {
        categoryModel = new CategoryModel();
        try {
            categoryModel.initializeCategoryFromDB();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }

        startBindings();

    }

    private void startBindings() {
        addCategoryButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());
        deleteCategoryButton.disableProperty().bind(categoryModel.categoryFxObjectPropertyProperty().isNull());
        editCategoryButton.disableProperty().bind(categoryModel.categoryFxObjectPropertyProperty().isNull());
        categoryComboBox.setItems(categoryModel.getCategoryFxObservableList());
        categoryTreeView.setRoot(categoryModel.getTreeItemRoot());
        categoryTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    categoryModel.getCategoryFxObjectProperty();
                }

        );
    }

    @FXML
    public void addCategoryOnAction() {
        try {
            categoryModel.saveCategoryToDB(categoryTextField.getText());
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        categoryTextField.clear();

    }

    @FXML
    public void onActionComboBox() {
        categoryModel.setCategoryFxObjectProperty(categoryComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void onActionEditCategory() {
        String newCategoryName = DialogsUtils.editDialog(categoryModel.getCategoryFxObjectProperty().getName());
        if (newCategoryName != null) {
            categoryModel.getCategoryFxObjectProperty().setName(newCategoryName);
            try {
                categoryModel.updateCategoryInDB();
            } catch (ApplicationException e) {
                DialogsUtils.errorDialogs(e.getMessage());
            }
        }

    }

    @FXML
    public void onActionDeleteButton() {
        try {
            categoryModel.deleteCategory();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    //todo
    @FXML
    public void deleteSelected() {
//        TreeItem c = (TreeItem) categoryTreeView.getSelectionModel().getSelectedItems();
//        c.getParent().getChildren().remove(c);
//
    }
}
