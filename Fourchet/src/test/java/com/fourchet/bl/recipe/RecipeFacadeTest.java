package com.fourchet.bl.recipe;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.recipe.Recipe;
import com.fourchet.recipe.TypeOfRecipe;
import com.fourchet.users.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeFacadeTest {

    @Test
    void save() throws Exception {
        List<String> Steps= List.of("test1", "test2", "test3");
        List<String> Ingredients= List.of("test1", "test2", "test3");
        TypeOfRecipe typeOfRecipe;
        RecipeFacade recipeFacade = RecipeFacade.getInstance();
        Recipe recipe = new Recipe("TitreTest","DescriptionTest","img",Steps,Ingredients,"qrgqerg",TypeOfRecipe.getType("DINNER"));
        recipeFacade.save(recipe);
        assertTrue(recipeFacade.getAll().contains(recipe));
    }

    @Test
    void update() {
        List<String> Steps= List.of("test1", "test2", "test3");
        List<String> Ingredients= List.of("test1", "test2", "test3");
        TypeOfRecipe typeOfRecipe;
        RecipeFacade recipeFacade = RecipeFacade.getInstance();
        Recipe recipe = new Recipe("New Titre","DescriptionTest","img",Steps,Ingredients,"qrgqerg",TypeOfRecipe.getType("DINNER"));
        Recipe oldrecipe = recipeFacade.getRecipeByFilter(new String[]{"TitreTest"}).get(0);
        recipeFacade.update(oldrecipe,recipe);
        assertTrue(recipeFacade.getRecipeByFilter(new String[]{"New Titre"}).contains(recipe));
    }

    @Test
    void getRecipeByFilter() {
        RecipeFacade recipeFacade = RecipeFacade.getInstance();
        List<Recipe> recipes = recipeFacade.getRecipeByFilter(new String[]{"New Titre"});
        assertTrue(recipes.size() == 1);
    }

}