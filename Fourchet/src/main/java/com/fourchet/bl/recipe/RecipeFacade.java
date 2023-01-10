package com.fourchet.bl.recipe;

import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.recipe.RecipeDao;
import com.fourchet.recipe.Recipe;
import com.fourchet.users.User;

import java.util.List;

public class RecipeFacade {
    private static RecipeFacade instance = null;

    private AbstractFactory abstractFactory;
    private RecipeDao recipeDao;
    private User currentUser;

    private RecipeFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.recipeDao = abstractFactory.getRecipeDao();
    }

    public static RecipeFacade getInstance() {
        if (instance == null) {
            instance = new RecipeFacade();
        }
        return instance;
    }

    public Recipe save(Recipe recipe) throws Exception
    {
        try {
            recipeDao.save(recipe);
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Error during the connection to the database");
        }
        return recipe;
    }

    public Recipe update(Recipe oldReciepe, Recipe newRecipe)
    {
        recipeDao.updateRecipe(oldReciepe,newRecipe);
        return newRecipe;
    }

    public void delete(Recipe recipe)
    {
        recipeDao.delete(recipe);
    }

    public List<Recipe> getAll() {
        return recipeDao.getAll();
    }

    public List<Recipe> getRecipeByFilter(String[] filter) {
        return recipeDao.getRecipeByFilter(filter);
    }

    public List<Recipe> findAllByAuthor(User user) {
        return recipeDao.findAllByAuthor(user);
    }

    public static void main(String[] args) {
        RecipeFacade recipeFacade = RecipeFacade.getInstance();
        recipeFacade.getAll();
    }
}