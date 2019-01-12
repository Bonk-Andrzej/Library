package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelsDb.Category;
import bonk_andrzej.app.fx.view.CategoryFx;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryConverterTest {

    private final CategoryConverter categoryConverter = new CategoryConverter();
    private final long idForConvert = 1L;
    private final String nameForConvert = "Kryminał";

    @Test
    public void shouldReturnCategoryWithTheSameFieldsLikeCategoryFx() {
        //given
        Category expectedCategory = setCategoryFields();
        CategoryFx categoryFx = setCategoryFxFields();
        //when
        Category categoryAfterConvert = categoryConverter.convertCategoryFxToCategory(categoryFx);
        //then
        assertThat(expectedCategory).isEqualTo(categoryAfterConvert);
    }
    @Test
    public void shouldReturnCategoryFxWithTheSameFieldsLikeCategory() {
        //given
        CategoryFx expectedCategoryFx = setCategoryFxFields();
        Category category = setCategoryFields();
        //when
        CategoryFx categoryFxAfterConvert = categoryConverter.convertCategoryToCategoryFx(category);
        //then
        //todo
        assertThat(categoryFxAfterConvert).isEqualToComparingFieldByField(expectedCategoryFx);
    }

    private Category setCategoryFields() {
        Category category = new Category();
        category.setId(idForConvert);
        category.setName(nameForConvert);
        return category;
    }

    private CategoryFx setCategoryFxFields() {
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(idForConvert);
        categoryFx.setName(nameForConvert);
        return categoryFx;
    }


}