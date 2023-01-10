package com.fourchet.bl.products;

import com.fourchet.products.Product;
import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.products.ProductsDao;
import com.fourchet.users.actitvities.Activity;

import java.util.List;

// This class below is a singleton class (we create the facade only once)
public class ProductsFacade {
    private static ProductsFacade instance = null;

    // The UserDaoFactory
    private AbstractFactory abstractFactory;
    // The UserDao
    private ProductsDao productsDao;


    private ProductsFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.productsDao = abstractFactory.getProductsDao();
    }

    public static ProductsFacade getInstance() {
        if (instance == null) {
            instance = new ProductsFacade();
        }
        return instance;
    }

    // delegate the Product dao to save the user
    public Product saveProduct(Product product)
    {
        try {
            Product existingProduct = productsDao.findByOwnerAndName(product.getOwnerEmail(), product.getOwnerActivityName(), product.getName());
            if (existingProduct == null) {
                productsDao.save(product);
            }
            else {
                System.out.println("Product already registered !");
                return null;
            }
        } catch (Exception e){
            // TODO : replace this by sending the message to the UI
            System.out.println(e.getMessage());
        }
        return product;
    }

    public List<Product> getAllProducts() {
        try {
            return productsDao.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteProduct(Product product) {
        productsDao.delete(product);
    }

    public void updateProduct(Product product, String[] params) {
        productsDao.update(product, params);
    }

    public List<Product> getAllByOwner(String ownerEmail, String ownerActivityName) {
        return productsDao.getAllByOwner(ownerEmail, ownerActivityName);
    }
}
