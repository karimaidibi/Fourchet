package com.fourchet.persist.typeOfCuisine;

import com.fourchet.persist.DaoFactory;
import com.fourchet.dishes.typeCuisine.TypeOfCuisine;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TypeOfCuisineDaoMongoDB extends TypeOfCuisineDao {

    // the ingredient categories dao knows the factory that created it
    DaoFactory factory;

    // get the collection of ingredient categories
    MongoCollection typeOfCuisineCollection;

    // the connection to the database
    MongoDatabase mongoDatabase;

    public TypeOfCuisineDaoMongoDB(DaoFactory factory) {
        this.factory = factory;
        this.mongoDatabase = factory.getMongoDatabase();
        this.typeOfCuisineCollection = mongoDatabase.getCollection("typeOfCuisine");
        System.out.println("Collection typeOfCuisine selected successfully");
    }


    // retourne l' ingredientCategory dont l'id est donné en paramètre
    @Override
    public Optional<TypeOfCuisine> get(long id) {
        //return Optional.ofNullable(users.get((int) id));
        return null;
    }

    /**
     * return all the categories in the database as a list of categories objects
     * it should replace the FindIterable<Document> by List<TypeOfCuisine> or add the founded categories to the list of categories
     */
    @Override
    public List<TypeOfCuisine> getAll() {
        List<TypeOfCuisine> res = new ArrayList<>();
        FindIterable<Document> docs = typeOfCuisineCollection.find();
        for (Document d : docs) {
            res.add(new TypeOfCuisine(d));
        }
        return res;
    }

    @Override
    public TypeOfCuisine findByName(String name) {
        Document query = new Document("name", name);
        FindIterable<Document> docs = typeOfCuisineCollection.find(query);
        if (docs.first() == null) {
            return null;
        }
        return new TypeOfCuisine(docs.first());
    }

    @Override
    public void save(TypeOfCuisine category){
        // create a new ingredient category
        // Create the document
        Document ingredientCategoryDocument = new Document("name", category.getName());

        // insert a user into the collection of users
        typeOfCuisineCollection.insertOne(ingredientCategoryDocument);
    }

    //
    @Override
    public void update(TypeOfCuisine category, String[] params) {
        Document oldIngredientCategoryDocument = new Document("name", category.getName());

        category.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        Document newIngredientCategoryDocument =  new Document("$set", new Document("name", category.getName()));
        typeOfCuisineCollection.updateMany(oldIngredientCategoryDocument, newIngredientCategoryDocument);
    }

    @Override
    public void delete(TypeOfCuisine category) {
        //delete the ingredient category
        //document to delete
        Document categoryDocument = new Document("name", category.getName());

        typeOfCuisineCollection.deleteOne(categoryDocument);
    }


}