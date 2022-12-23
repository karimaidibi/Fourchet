package com.fourchet.bl.ingredients;

import com.fourchet.ingredients.Ingredient;
import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.ingredients.IngredientsDao;

import java.util.ArrayList;
import java.util.List;

// This class below is a singleton class (we create the facade only once)
public class IngredientsFacade {
    private static IngredientsFacade instance = null;

    // The UserDaoFactory
    private AbstractFactory abstractFactory;
    // The UserDao
    private IngredientsDao ingredientsDao;


    private IngredientsFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.ingredientsDao = abstractFactory.getIngredientsDao();
    }

    public static IngredientsFacade getInstance() {
        if (instance == null) {
            instance = new IngredientsFacade();
        }
        return instance;
    }

    // delegate the Ingredient dao to save the user
    public Ingredient saveIngredient(Ingredient ingredient)
    {
        try {
            Ingredient existingIngredient = ingredientsDao.findByName(ingredient.getName());
            if (existingIngredient != null) {
                ingredientsDao.save(ingredient);
            }
            else {
                System.out.println("Ingredient already registered !");
                return null;
            }
        } catch (Exception e){
            // TODO : replace this by sending the message to the UI
            System.out.println(e.getMessage());
        }
        return ingredient;
    }

    public List<Ingredient> getAllIngredients() {
        try {
            return ingredientsDao.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteIngredient(Ingredient ingredient) {
        ingredientsDao.delete(ingredient);
    }
}
