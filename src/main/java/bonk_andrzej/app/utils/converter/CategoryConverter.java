package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelsDb.Category;
import bonk_andrzej.app.fx.view.CategoryFx;

public class CategoryConverter {

    public CategoryFx convertCategoryToCategoryFx(Category category) {
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(category.getId());
        categoryFx.setName(category.getName());
        return categoryFx;
    }

    public Category convertCategoryFxToCategory(CategoryFx categoryFx) {
        Category category = new Category();
        category.setId(categoryFx.getId());
        category.setName(categoryFx.getName());
        return category;
    }
}
