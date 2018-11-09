package app.utils.converter;

import app.db.modelsDb.Category;
import app.fx.view.CategoryFx;

public class CategoryConverter {

    public static CategoryFx convertFromCategoryToCategoryFx(Category category){
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(category.getId());
        categoryFx.setName(category.getName());
        return categoryFx;
    }

    public static Category convertFromCategoryFxToCategory(CategoryFx categoryFx){
        Category category = new Category();
        category.setId(categoryFx.getId());
        category.setName(categoryFx.getName());
        return category;
    }
}
