package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelsDb.Category;
import bonk_andrzej.app.fx.view.CategoryFx;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryConverterTest extends AbstractTest{

    private final CategoryConverter categoryConverter = new CategoryConverter();

    @Test
    void shouldReturnCategoryWithTheSameFieldsLikeCategoryFx() {
        //given
        Category expectedCategory = makeCategory();
        CategoryFx categoryFx = makeCategoryFx();
        //when
        Category categoryAfterConvert = categoryConverter.convertCategoryFxToCategory(categoryFx);
        //then
        assertThat(categoryAfterConvert).isEqualTo(expectedCategory);
    }

    @Test
    void shouldReturnCategoryFxWithTheSameFieldsLikeCategory() {
        //given
        Category category = makeCategory();
        CategoryFx expectedCategoryFx = makeCategoryFx();
        //when
        CategoryFx categoryFxAfterConvert = categoryConverter.convertCategoryToCategoryFx(category);
        //then
        assertThat(categoryFxAfterConvert.getName()).isEqualTo(expectedCategoryFx.getName());
    }

   private  Category makeCategory(){
        return super.createCategory();
   }

    private  CategoryFx makeCategoryFx(){
        return super.createCategoryFx();
    }

}