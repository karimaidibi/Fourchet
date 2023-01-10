package com.fourchet.persist.productCategories;

import com.fourchet.products.ProductCategory;
import com.fourchet.persist.DaoFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductCategoriesDaoMongoDB extends ProductCategoriesDao {

    // the product categories dao knows the factory that created it
    DaoFactory factory;

    // get the collection of product categories
    MongoCollection productCategoriesCollection;

    // the connection to the database
    MongoDatabase mongoDatabase;

    public ProductCategoriesDaoMongoDB(DaoFactory factory) {
        this.factory = factory;
        this.mongoDatabase = factory.getMongoDatabase();
        this.productCategoriesCollection = mongoDatabase.getCollection("productCategories");
        System.out.println("Collection productCategories selected successfully");
    }


    // retourne l' productCategory dont l'id est donné en paramètre
    @Override
    public Optional<ProductCategory> get(long id) {
        //return Optional.ofNullable(users.get((int) id));
        return null;
    }

    /**
     * return all the categories in the database as a list of categories objects
     * it should replace the FindIterable<Document> by List<Category> or add the founded categories to the list of categories
     */
    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> res = new ArrayList<>();
        FindIterable<Document> docs = productCategoriesCollection.find();
        for (Document d : docs) {
            res.add(new ProductCategory(d));
        }
        return res;
    }

    @Override
    public ProductCategory findByName(String name) {
        Document query = new Document("name", name);
        FindIterable<Document> docs = productCategoriesCollection.find(query);
        if (docs.first() == null) {
            return null;
        }
        return new ProductCategory(docs.first());
    }

    @Override
    public void save(ProductCategory category){
        // create a new product category
        // Create the document
        Document productCategoryDocument = new Document("name", category.getName());

        // insert a user into the collection of users
        productCategoriesCollection.insertOne(productCategoryDocument);
    }

    //
    @Override
    public void update(ProductCategory category, String[] params) {
        Document oldProductCategoryDocument = new Document("name", category.getName());

        category.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        Document newProductCategoryDocument =  new Document("$set", new Document("name", category.getName()));
        productCategoriesCollection.updateMany(oldProductCategoryDocument, newProductCategoryDocument);
    }

    @Override
    public void delete(ProductCategory category) {
        //delete the product category
        //document to delete
        Document categoryDocument = new Document("name", category.getName());

        productCategoriesCollection.deleteOne(categoryDocument);
    }


}