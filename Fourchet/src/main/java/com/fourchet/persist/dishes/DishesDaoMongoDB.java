package com.fourchet.persist.dishes;

import com.fourchet.dishes.Dish;
import com.fourchet.persist.DaoFactory;
import com.fourchet.users.actitvities.Activity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DishesDaoMongoDB extends DishesDao {

    // the ingredient categories dao knows the factory that created it
    DaoFactory factory;

    // get the collection of ingredient categories
    MongoCollection dishesCollection;

    // the connection to the database
    MongoDatabase mongoDatabase;

    public DishesDaoMongoDB(DaoFactory factory) {
        this.factory = factory;
        this.mongoDatabase = factory.getMongoDatabase();
        this.dishesCollection = mongoDatabase.getCollection("dishes");
        System.out.println("Collection dishes selected successfully");
    }


    // retourne l' ingredientdish dont l'id est donné en paramètre
    @Override
    public Optional<Dish> get(long id) {
        //return Optional.ofNullable(users.get((int) id));
        return null;
    }

    @Override
    public Dish findByActivityAndName(Activity activity, String name) {
        return null;
    }

    @Override
    public List<Dish> getAllByActivity(Activity activity) {
        return null;
    }

    @Override
    public List<Dish> getAll() {
        List<Dish> res = new ArrayList<>();
        FindIterable<Document> docs = dishesCollection.find();
        for (Document d : docs) {
            res.add(new Dish(d));
        }
        return res;
    }

    @Override
    public void save(Dish dish){
        // create a new ingredient dish
        // Create the document
        Document dishDocument = new Document("name", dish.getTitle());

        // insert a user into the collection of users
        dishesCollection.insertOne(dishDocument);
    }

    //
    @Override
    public void update(Dish dish, String[] params) {
        Document olddishDocument = new Document("title", dish.getTitle());

        dish.setTitle(Objects.requireNonNull(
                params[0], "Title cannot be null"));
        Document newdishDocument =  new Document("$set", new Document("title", dish.getTitle()));
        dishesCollection.updateMany(olddishDocument, newdishDocument);
    }

    @Override
    public void delete(Dish dish) {
        //delete the ingredient dish
        //document to delete
        Document dishDocument = new Document("title", dish.getTitle());

        dishesCollection.deleteOne(dishDocument);
    }


}