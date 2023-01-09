package com.fourchet.persist;

import com.fourchet.persist.account.UserDao;
import com.fourchet.persist.recipe.RecipeDao;

// THis class below is a singleton class
public abstract class AbstractFactory {
    private static DaoFactory instance = null;


    protected AbstractFactory() {}

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public abstract UserDao getUserDao();

    public abstract RecipeDao getRecipeDao();

}
