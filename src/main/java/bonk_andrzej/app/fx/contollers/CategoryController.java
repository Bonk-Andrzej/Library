package bonk_andrzej.app.fx.contollers;

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
    private void initialize() {
        categoryModel = new CategoryModel();
        try {
            categoryModel.initializeCategoryFromDB();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        disableButtoms();
        bindProperties();
    }

    @FXML
    private void addCategoryOnAction() {
        try {
            categoryModel.saveOrUpdateCategoryInDB();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        categoryTextField.clear();
    }

    @FXML
    private void onActionDeleteButton() {
        try {
            categoryModel.deleteCategory();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
    }

    @FXML
    private void onActionComboBox() {
        categoryModel.setCategoryFxObjectProperty(categoryComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onActionEditCategory() {
        String newCategoryName = DialogsUtils.editDialog(categoryModel.getCategoryFxObjectProperty().getName());
        if (newCategoryName != null) {
            categoryModel.getCategoryFxObjectProperty().setName(newCategoryName);
            try {
                categoryModel.saveOrUpdateCategoryInDB();
            } catch (ApplicationException e) {
                DialogsUtils.errorDialogs(e.getMessage());
            }
        }
    }

    private void bindProperties() {
        categoryComboBox.setItems(categoryModel.getCategoryFxObservableList());
        categoryTreeView.setRoot(categoryModel.getTreeItemRoot());
        categoryModel.getCategoryFxObjectProperty().nameProperty().bind(categoryTextField.textProperty());
    }

    private void disableButtoms() {
        addCategoryButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());
        deleteCategoryButton.disableProperty().bind(categoryComboBox.valueProperty().isNull());
        editCategoryButton.disableProperty().bind(categoryComboBox.valueProperty().isNull());
        //        categoryTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//                    categoryModel.getCategoryFx();
//                }
//        );
    }


    //todo
    @FXML
    private void deleteSelected() {
//        TreeItem c = (TreeItem) categoryTreeView.getSelectionModel().getSelectedItems();
//        c.getParent().getChildren().remove(c);
//
    }
}
