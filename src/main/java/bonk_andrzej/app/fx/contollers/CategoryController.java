package bonk_andrzej.app.fx.contollers;

import bonk_andrzej.app.fx.view.CategoryFx;
import bonk_andrzej.app.fx.modelsFx.CategoryModel;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class CategoryController extends TextField {

    @FXML
    public MenuItem deleteContextMenu;
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
        disableButtons();
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
        Optional<ButtonType> result = DialogsUtils.confirmAlert("delete.category.title", "delete.category.header");
        if (result.get() == ButtonType.OK) {
            try {
                categoryModel.deleteCategory();
            } catch (ApplicationException e) {
                DialogsUtils.errorDialogs(e.getMessage());
            }
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
    @FXML
    private void bindProperties() {
        categoryComboBox.setItems(categoryModel.getCategoryFxObservableList());
        categoryTreeView.setRoot(categoryModel.getTreeItemRoot());
        categoryModel.getCategoryFxObjectProperty().nameProperty().bind(categoryTextField.textProperty());
    }

    private void disableButtons() {
        addCategoryButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());
        deleteCategoryButton.disableProperty().bind(categoryComboBox.valueProperty().isNull());
        editCategoryButton.disableProperty().bind(categoryComboBox.valueProperty().isNull());
    }


}
