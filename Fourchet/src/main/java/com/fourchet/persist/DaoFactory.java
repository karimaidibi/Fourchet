package com.fourchet.persist;

import com.fourchet.persist.account.UserDao;
import com.fourchet.persist.account.UserDaoMongoDB;
import com.fourchet.persist.account.activities.ActivitiesDao;
import com.fourchet.persist.account.activities.ActivitiesDaoMongoDB;
import com.fourchet.persist.dishes.DishesDao;
import com.fourchet.persist.dishes.DishesDaoMongoDB;
import com.fourchet.persist.ingredientCategories.IngredientCategoriesDao;
import com.fourchet.persist.ingredientCategories.IngredientCategoriesDaoMongoDB;
import com.fourchet.persist.ingredients.IngredientsDao;
import com.fourchet.persist.ingredients.IngredientsDaoMongoDB;
import com.fourchet.persist.productCategories.ProductCategoriesDao;
import com.fourchet.persist.productCategories.ProductCategoriesDaoMongoDB;
import com.fourchet.persist.products.ProductsDao;
import com.fourchet.persist.products.ProductsDaoMongoDB;
import com.fourchet.persist.recipe.RecipeDao;
import com.fourchet.persist.recipe.RecipeDaoMongoDB;
import com.fourchet.persist.payments.PaymentDao;
import com.fourchet.persist.payments.PaymentDaoMongoDB;
import com.fourchet.persist.typeOfCuisine.TypeOfCuisineDao;
import com.fourchet.persist.typeOfCuisine.TypeOfCuisineDaoMongoDB;
import com.mongodb.client.MongoDatabase;

public class DaoFactory extends AbstractFactory {

    private UserDao userDao = null;
    private IngredientsDao ingredientsDao = null;

    private IngredientCategoriesDao ingredientCategoriesDao = null;

    private TypeOfCuisineDao typeOfCuisineDao = null;

    private ActivitiesDao activitiesDao = null;
    private RecipeDao recipeDao = null;
    private DishesDao dishesDao = null;
    private ProductsDao productsDao = null;
    private ProductCategoriesDao productCategoriesDao = null;
    private PaymentDao paymentDao = null;

    // instanciate the database class
    private MongoDB mongoDB = new MongoDB();

    public DaoFactory() {
        super();
    }

    public MongoDatabase getMongoDatabase() {
        return this.mongoDB.getDBConnection();
    }

    @Override
    public UserDao getUserDao() {
        if (userDao==null) {
            this.userDao = new UserDaoMongoDB(this);
        }
        return userDao;
    }
    @Override
    public IngredientsDao getIngredientsDao() {
        if (ingredientsDao==null) {
            this.ingredientsDao = new IngredientsDaoMongoDB(this);
        }
        return ingredientsDao;
    }

    @Override
    public IngredientCategoriesDao getIngredientCategoriesDao() {
        if (ingredientCategoriesDao==null) {
            this.ingredientCategoriesDao = new IngredientCategoriesDaoMongoDB(this);
        }
        return ingredientCategoriesDao;
    }

    @Override
    public TypeOfCuisineDao getTypeOfCuisineDao() {
        if (typeOfCuisineDao==null) {
            this.typeOfCuisineDao = new TypeOfCuisineDaoMongoDB(this);
        }
        return typeOfCuisineDao;
    }

    @Override
    public ActivitiesDao getActivitiesDao() {
        if (activitiesDao==null) {
            this.activitiesDao = new ActivitiesDaoMongoDB(this);
        }
        return activitiesDao;
    }

    @Override
    public RecipeDao getRecipeDao() {
        if (recipeDao==null) {
            this.recipeDao = new RecipeDaoMongoDB(this);
        }
        return recipeDao;
    }

    @Override
    public PaymentDao getPaymentDao() {
        if (paymentDao==null) {
            this.paymentDao = new PaymentDaoMongoDB(this);
        }
        return paymentDao;
    }

    @Override
    public DishesDao getDishesDao() {
        if (dishesDao==null) {
            this.dishesDao = new DishesDaoMongoDB(this);
        }
        return dishesDao;
    }

    @Override
    public ProductsDao getProductsDao() {
        if (productsDao==null) {
            this.productsDao = new ProductsDaoMongoDB(this);
        }
        return productsDao;
    }

    @Override
    public ProductCategoriesDao getProductCategoriesDao() {
        if (productCategoriesDao==null) {
            this.productCategoriesDao = new ProductCategoriesDaoMongoDB(this);
        }
        return productCategoriesDao;
    }

    //close the database connection
    public void closeConnectionToDB() {
        // Close the connection
        this.mongoDB.closeConnection();
    }

}
