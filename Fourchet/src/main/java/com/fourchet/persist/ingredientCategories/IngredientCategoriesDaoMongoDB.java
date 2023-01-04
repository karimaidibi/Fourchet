package com.fourchet.persist.ingredientCategories;

import com.fourchet.ingredients.IngredientCategory;
import com.fourchet.persist.DaoFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class IngredientCategoriesDaoMongoDB extends IngredientCategoriesDao {

    // the ingredient categories dao knows the factory that created it
    DaoFactory factory;

    // get the collection of ingredient categories
    MongoCollection ingredientCategoriesCollection;

    // the connection to the database
    MongoDatabase mongoDatabase;

    public IngredientCategoriesDaoMongoDB(DaoFactory factory) {
        this.factory = factory;
        this.mongoDatabase = factory.getMongoDatabase();
        this.ingredientCategoriesCollection = mongoDatabase.getCollection("ingredientCategories");
        System.out.println("Collection ingredientCategories selected successfully");
    }


    // retourne l' ingredientCategory dont l'id est donné en paramètre
    @Override
    public Optional<IngredientCategory> get(long id) {
        //return Optional.ofNullable(users.get((int) id));
        return null;
    }

    /**
     * return all the categories in the database as a list of categories objects
     * it should replace the FindIterable<Document> by List<Category> or add the founded categories to the list of categories
     */
    @Override
    public List<IngredientCategory> getAll() {
        List<IngredientCategory> res = new ArrayList<>();
        FindIterable<Document> docs = ingredientCategoriesCollection.find();
        for (Document d : docs) {
            res.add(new IngredientCategory(d));
        }
        return res;
    }

    @Override
    public IngredientCategory findByName(String name) {
        Document query = new Document("name", name);
        FindIterable<Document> docs = ingredientCategoriesCollection.find(query);
        if (docs.first() == null) {
            return null;
        }
        return new IngredientCategory(docs.first());
    }

    @Override
    public void save(IngredientCategory category){
        // create a new ingredient category
        // Create the document
        Document ingredientCategoryDocument = new Document("name", category.getName());

        // insert a user into the collection of users
        ingredientCategoriesCollection.insertOne(ingredientCategoryDocument);
    }

    //
    @Override
    public void update(IngredientCategory category, String[] params) {
        Document oldIngredientCategoryDocument = new Document("name", category.getName());

        category.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        Document newIngredientCategoryDocument =  new Document("$set", new Document("name", category.getName()));
        ingredientCategoriesCollection.updateMany(oldIngredientCategoryDocument, newIngredientCategoryDocument);
    }

    @Override
    public void delete(IngredientCategory category) {
        //delete the ingredient category
        //document to delete
        Document categoryDocument = new Document("name", category.getName());

        ingredientCategoriesCollection.deleteOne(categoryDocument);
    }


}