package com.fourchet.persist;

import com.fourchet.persist.account.UserDao;
import com.fourchet.persist.account.activities.ActivitiesDao;
import com.fourchet.persist.ingredientCategories.IngredientCategoriesDao;
import com.fourchet.persist.ingredients.IngredientsDao;
import com.fourchet.persist.payments.PaymentDao;
import com.fourchet.persist.typeOfCuisine.TypeOfCuisineDao;

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
    public abstract IngredientsDao getIngredientsDao();

    public abstract IngredientCategoriesDao getIngredientCategoriesDao();


    public abstract TypeOfCuisineDao getTypeOfCuisineDao();

    public abstract ActivitiesDao getActivitiesDao();

    public abstract PaymentDao getPaymentDao();


}
