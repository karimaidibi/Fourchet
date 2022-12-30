package com.fourchet.bl.ingredients;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.bl.ingredientCategories.IngredientCategoriesFacade;
import com.fourchet.ingredients.Ingredient;
import com.fourchet.ingredients.IngredientCategory;
import com.fourchet.users.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientsFacadeTest {

    @Test
    void saveIngredient() {
        try {
            IngredientsFacade facade = IngredientsFacade.getInstance();
            Ingredient ingredient = facade.saveIngredient(new Ingredient("test", new IngredientCategory("test")));
            assertTrue(ingredient.getName().equals("test") && ingredient.getCategory().getName().equals("test"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void deleteIngredient() {
    }

    @Test
    void findByName() {
        try {
            IngredientsFacade facade = IngredientsFacade.getInstance();
            assertTrue(facade.findByName("test").getName().equals("test"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void updateIngredient() {
        try {
            IngredientsFacade facade = IngredientsFacade.getInstance();
            Ingredient oldIngredient = new Ingredient("test", new IngredientCategory("test"));
            String[] params = {"test02", "test002"};
            facade.updateIngredient(oldIngredient, params);
            assertTrue(facade.findByName("test") == null && facade.findByName("test02").getName().equals("test02"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}