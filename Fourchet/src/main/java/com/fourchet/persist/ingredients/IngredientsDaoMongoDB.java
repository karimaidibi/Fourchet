package com.fourchet.persist.ingredients;

import com.fourchet.ingredients.Ingredient;
import com.fourchet.ingredients.IngredientCategory;
import com.fourchet.persist.DaoFactory;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class IngredientsDaoMongoDB extends IngredientsDao {

    // the ingredient dao knows the factory that created it
    DaoFactory factory;

    // get the collection of users
    MongoCollection ingredientsCollection;

    // the connection to the database
    MongoDatabase mongoDatabase;

    public IngredientsDaoMongoDB(DaoFactory factory) {
        this.factory = factory;
        this.mongoDatabase = factory.getMongoDatabase();
        this.ingredientsCollection = mongoDatabase.getCollection("ingredients");
        System.out.println("Collection ingredients selected successfully");
    }


    // retourne l'ingredient dont l'id est donné en paramètre
    @Override
    public Optional<Ingredient> get(long id) {
        //return Optional.ofNullable(users.get((int) id));
        return null;
    }

    /**
     * return all the ingredients in the database as a list of ingredients objects
     * it should replace the FindIterable<Document> by List<Ingredient> or add the founded ingredients to the list of ingredients
     */
    @Override
    public List<Ingredient> getAll() {
        List<Ingredient> res = new ArrayList<>();
        FindIterable<Document> docs = ingredientsCollection.find();
        for (Document d : docs) {
            res.add(new Ingredient(d));
        }
        return res;
    }

    @Override
    public Ingredient findByName(String name) {
        Document query = new Document("name", name);
        FindIterable<Document> docs = ingredientsCollection.find(query);
        if (docs.first() == null) {
            return null;
        }
        return new Ingredient(docs.first());
    }

    @Override
    public void save(Ingredient ingredient){
        // create a new ingredient
        // Create the document
        Document ingredientDocument = new Document("name", ingredient.getName())
                .append("category", ingredient.getCategory().getName());

        // insert a ingredient into the collection of ingredients
        try {
            ingredientsCollection.insertOne(ingredientDocument);
        }
        catch (MongoException e) {
            System.out.println(e.getMessage());
        }
    }

    //
    @Override
    public void update(Ingredient ingredient, String[] params) {
        ingredient.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        IngredientCategory category = new IngredientCategory(params[1]);
        ingredient.setCategory(category);
    }

    @Override
    public void delete(Ingredient ingredient) {
        //delete the ingredient
        //document to delete
        Document ingredientDocument = new Document("name", ingredient.getName())
                .append("category", ingredient.getCategory());

        ingredientsCollection.deleteOne(ingredientDocument);
    }


}