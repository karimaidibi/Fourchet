package com.fourchet.bl.ingredientCategories;

import com.fourchet.bl.ingredients.IngredientsFacade;
import com.fourchet.ingredients.Ingredient;
import com.fourchet.ingredients.IngredientCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCategoriesFacadeTest {

    @Test
    void saveIngredientCategory() {
        try {
            IngredientCategoriesFacade facade = IngredientCategoriesFacade.getInstance();
            IngredientCategory category = facade.saveIngredientCategory(new IngredientCategory("test"));
            assertTrue(category.getName().equals("test"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void findByName() {
        try {
            IngredientCategoriesFacade facade = IngredientCategoriesFacade.getInstance();
            assertTrue(facade.findByName("test").getName().equals("test"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void updateIngredientCategory() {
        try {
            IngredientCategoriesFacade facade = IngredientCategoriesFacade.getInstance();
            IngredientCategory oldCategory = new IngredientCategory("test");
            String[] params = {"test02"};
            facade.updateIngredientCategory(oldCategory, params);
            assertTrue(facade.findByName("test") == null && facade.findByName("test02").getName().equals("test02"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}