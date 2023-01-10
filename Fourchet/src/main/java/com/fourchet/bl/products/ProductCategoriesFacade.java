package com.fourchet.bl.products;

import com.fourchet.products.ProductCategory;
import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.productCategories.ProductCategoriesDao;

import java.text.ParseException;
import java.util.List;

// This class below is a singleton class (we create the facade only once)
public class ProductCategoriesFacade {
    private static ProductCategoriesFacade instance = null;

    // The UserDaoFactory
    private AbstractFactory abstractFactory;
    // The UserDao
    private ProductCategoriesDao productCategoriesDao;


    private ProductCategoriesFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.productCategoriesDao = abstractFactory.getProductCategoriesDao();
    }

    public static ProductCategoriesFacade getInstance() {
        if (instance == null) {
            instance = new ProductCategoriesFacade();
        }
        return instance;
    }

    // delegate the Ingredient dao to save the user
    public ProductCategory saveProductCategory(ProductCategory category)
    {
        try {
            ProductCategory existingProductCategory = productCategoriesDao.findByName(category.getName());
            if (existingProductCategory == null) {
                productCategoriesDao.save(category);
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

    public void deleteProductCategory(ProductCategory category) {
        productCategoriesDao.delete(category);
    }

    public void updateProductCategory(ProductCategory category, String[] params) {
        productCategoriesDao.update(category, params);
    }

    public ProductCategory findByName(String name) {
        return productCategoriesDao.findByName(name);
    }

    public List<ProductCategory> getAllCategories() throws ParseException {
        return productCategoriesDao.getAll();
    }

}
