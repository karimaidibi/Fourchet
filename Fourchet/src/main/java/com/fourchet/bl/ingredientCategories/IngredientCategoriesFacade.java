package com.fourchet.bl.ingredientCategories;

import com.fourchet.ingredients.Ingredient;
import com.fourchet.ingredients.IngredientCategory;
import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.ingredientCategories.IngredientCategoriesDao;
import com.fourchet.persist.ingredients.IngredientsDao;

import java.util.ArrayList;
import java.util.List;

// This class below is a singleton class (we create the facade only once)
public class IngredientCategoriesFacade {
    private static IngredientCategoriesFacade instance = null;

    // The UserDaoFactory
    private AbstractFactory abstractFactory;
    // The UserDao
    private IngredientCategoriesDao ingredientCategoriesDao;


    private IngredientCategoriesFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.ingredientCategoriesDao = abstractFactory.getIngredientCategoriesDao();
    }

    public static IngredientCategoriesFacade getInstance() {
        if (instance == null) {
            instance = new IngredientCategoriesFacade();
        }
        return instance;
    }

    // delegate the Ingredient dao to save the user
    public IngredientCategory saveIngredientCategory(IngredientCategory category)
    {
        try {
            IngredientCategory existingIngredientCategory = ingredientCategoriesDao.findByName(category.getName());
            if (existingIngredientCategory == null) {
                ingredientCategoriesDao.save(category);
            }
            else {
                System.out.println("Category already registered !");
                return null;
            }
        } catch (Exception e){
            // TODO : replace this by sending the message to the UI
            System.out.println(e.getMessage());
        }
        return category;
    }

    public void deleteIngredientCategory(IngredientCategory category) {
        ingredientCategoriesDao.delete(category);
    }

    public void updateIngredientCategory(IngredientCategory category, String[] params) {
        ingredientCategoriesDao.update(category, params);
    }

    public List<IngredientCategory> getAllCategories() {
        return ingredientCategoriesDao.getAll();
    }

}
