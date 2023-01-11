package com.fourchet.bl.products;

import com.fourchet.persist.DaoFactory;
import com.fourchet.persist.products.ProductsDaoMongoDB;
import com.fourchet.products.Product;
import com.fourchet.products.ProductCategory;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.lang.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductsFacadeTest {

    /**
     * This method will create a product with a float price, the save it to database
     * then it will get the product from the database. Then it will
     * asserts that the attribute price of the object product collected from the database is of type double
     * */
    @Test
    void saveProduct() throws ParseException {
        ProductsFacade productsFacade = ProductsFacade.getInstance();
        ProductCategory productCategory = new ProductCategory("test");
        Product p = new Product("test","test","test",productCategory,0.0f);
        productsFacade.saveProduct(p);
        DaoFactory daoFactory = DaoFactory.getInstance();
        ProductsDaoMongoDB productsDaoMongoDB = (ProductsDaoMongoDB) daoFactory.getProductsDao();
        Product product = productsDaoMongoDB.findByOwnerAndName("test","test","test");
        assertTrue((Double)product.getPrice() instanceof Double);
    }
}