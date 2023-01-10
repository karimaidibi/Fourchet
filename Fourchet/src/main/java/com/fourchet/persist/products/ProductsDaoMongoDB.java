package com.fourchet.persist.products;

import com.fourchet.products.Product;
import com.fourchet.products.ProductCategory;
import com.fourchet.persist.DaoFactory;
import com.fourchet.users.actitvities.Activity;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductsDaoMongoDB extends ProductsDao {

    // the product dao knows the factory that created it
    DaoFactory factory;

    MongoCollection productsCollection;

    // the connection to the database
    MongoDatabase mongoDatabase;

    public ProductsDaoMongoDB(DaoFactory factory) {
        this.factory = factory;
        this.mongoDatabase = factory.getMongoDatabase();
        this.productsCollection = mongoDatabase.getCollection("products");
        System.out.println("Collection products selected successfully");
    }


    // retourne l'product dont l'id est donné en paramètre
    @Override
    public Optional<Product> get(long id) {
        //return Optional.ofNullable(users.get((int) id));
        return null;
    }

    @Override
    public Product findByOwnerAndName(String ownerEmail, String ownerActivityName, String name) throws ParseException {
        Document query = new Document("name", name)
                .append("ownerEmail", ownerEmail)
                .append("ownerActivityName", ownerActivityName);
        FindIterable<Document> docs = productsCollection.find(query);
        if (docs.first() == null) {
            return null;
        }
        return new Product(docs.first());
    }

    /**
     * return all the products in the database as a list of products objects
     * it should replace the FindIterable<Document> by List<Product> or add the founded products to the list of products
     */
    @Override
    public List<Product> getAll() throws ParseException {
        List<Product> res = new ArrayList<>();
        FindIterable<Document> docs = productsCollection.find();
        for (Document d : docs) {
            res.add(new Product(d));
        }
        return res;
    }

    @Override
    public void save(Product product){
        // create a new product
        // Create the document
        Document productDocument = new Document("ownerEmail", product.getOwnerEmail())
                .append("ownerActivityName", product.getOwnerActivityName())
                .append("name", product.getName())
                .append("category", product.getCategory().getName())
                .append("price", product.getPrice());

        // insert a product into the collection of products
        try {
            productsCollection.insertOne(productDocument);
        }
        catch (MongoException e) {
            System.out.println(e.getMessage());
        }
    }

    //
    @Override
    public void update(Product product, String[] params) {
        Document oldProductDocument = new Document("ownerEmail", product.getOwnerEmail())
                .append("ownerActivityName", product.getOwnerActivityName())
                .append("name", product.getName())
                .append("category", product.getCategory().getName())
                .append("price", product.getPrice());

        product.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        ProductCategory category = new ProductCategory(params[1]);
        product.setCategory(category);
        Document newProductDocument =  new Document("$set", new Document("ownerEmail", product.getOwnerEmail())
                .append("ownerActivityName", product.getOwnerActivityName())
                .append("name", product.getName())
                .append("category", product.getCategory().getName())
                .append("price", product.getPrice()));
        productsCollection.updateMany(oldProductDocument, newProductDocument);
    }


    @Override
    public List<Product> getAllByOwner(String ownerEmail, String ownerActivityName) throws ParseException {
        List<Product> res = new ArrayList<>();
        Document query = new Document("ownerEmail", ownerEmail)
                .append("ownerActivityName", ownerActivityName);
        FindIterable<Document> docs = productsCollection.find(query);
        for (Document d : docs) {
            res.add(new Product(d));
        }
        return res;
    }

    @Override
    public void delete(Product product) {
        //delete the product
        //document to delete
        Document productDocument = new Document("ownerEmail", product.getOwnerEmail())
                .append("ownerActivityName", product.getOwnerActivityName())
                .append("name", product.getName())
                .append("category", product.getCategory().getName())
                .append("price", product.getPrice());

        productsCollection.deleteMany(productDocument);
    }

    public void deleteAll() {
        //delete the product
        //document to delete
        Document productDocument = new Document();

        productsCollection.deleteMany(productDocument);
    }


}