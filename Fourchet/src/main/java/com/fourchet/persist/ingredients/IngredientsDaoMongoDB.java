package com.fourchet.persist.ingredients;

import com.fourchet.ingredients.Ingredient;
import com.fourchet.ingredients.IngredientCategory;
import com.fourchet.persist.DaoFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class IngredientsDaoMongoDB extends IngredientsDao {

    // the user dao knows the factory that created it
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


    // retourne l'user dont l'id est donné en paramètre
    @Override
    public Optional<Ingredient> get(long id) {
        //return Optional.ofNullable(users.get((int) id));
        return null;
    }

    /**
     * return all the users in the database as a list of users objects
     * it should replace the FindIterable<Document> by List<User> or add the founded users to the list of users
     */
    @Override
    public List<Ingredient> getAll() {
        return null;
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
                .append("category", ingredient.getCategory());

        // insert a user into the collection of users
        ingredientsCollection.insertOne(ingredientDocument);
    }

    //
    @Override
    public void update(Ingredient ingredient, String[] params) {
        ingredient.setName(Objects.requireNonNull(
                params[0], "Email cannot be null"));
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