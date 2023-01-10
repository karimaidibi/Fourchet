package com.fourchet.persist;

import com.fourchet.persist.account.UserDao;
import com.fourchet.persist.account.activities.ActivitiesDao;
import com.fourchet.persist.dishes.DishesDao;
import com.fourchet.persist.ingredientCategories.IngredientCategoriesDao;
import com.fourchet.persist.ingredients.IngredientsDao;
import com.fourchet.persist.payments.PaymentDao;
import com.fourchet.persist.productCategories.ProductCategoriesDao;
import com.fourchet.persist.products.ProductsDao;
import com.fourchet.persist.recipe.RecipeDao;
import com.fourchet.persist.review.ReviewDao;
import com.fourchet.persist.typeOfCuisine.TypeOfCuisineDao;
import com.fourchet.products.ProductCategory;

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

    public abstract RecipeDao getRecipeDao();

    public abstract PaymentDao getPaymentDao();

    public abstract DishesDao getDishesDao();

    public abstract ProductsDao getProductsDao();
    public abstract ProductCategoriesDao getProductCategoriesDao();
    public abstract ReviewDao getReviewDao();
}
