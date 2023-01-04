package com.fourchet.persist.account;

import com.fourchet.persist.DaoFactory;
import com.fourchet.users.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class UserDaoMongoDB extends UserDao {

    // the user dao knows the factory that created it
    DaoFactory factory;

    // get the collection of users
    MongoCollection usersCollection;

    // the connection to the database
    MongoDatabase mongoDatabase;

    public UserDaoMongoDB(DaoFactory factory) {
        this.factory = factory;
        this.mongoDatabase = factory.getMongoDatabase();
        this.usersCollection = mongoDatabase.getCollection("users");
        System.out.println("Collection users selected successfully");
    }

    // retourne les users qui sont provider dans la base
    public List<User> getProviders() {
        return null;
    }

    // retourne les users qui sont client dans la base
    public List<User> getClients() {
        return null;
    }


    // retourne l'user dont l'id est donné en paramètre
    @Override
    public Optional<User> get(long id) {
        //return Optional.ofNullable(users.get((int) id));
        return null;
    }

    /**
     * return all the users in the database as a list of users objects
     * it should replace the FindIterable<Document> by List<User> or add the founded users to the list of users
     */
    @Override
    public List<User> getAll() {
        List users = new ArrayList<User>();
        // A request to Find all documents in the collection of users
        FindIterable<Document> documents = usersCollection.find();
        System.out.println("Documents found successfully");
        System.out.println(documents.first());
        // Iterate through the documents
        for (Document doc : documents) {
            System.out.println(doc);
            users.add(new User(doc));
        }
        return users;
    }

    @Override
    public void save(User user){
        // create a new user
        // Create the document
        Document userDocument = new Document("username", user.getUsername())
                .append("email", user.getEmail())
                .append("role", user.getRole())
                .append("password", user.getPassword())
                .append("picture", user.getPicture());

        // insert a user into the collection of users
        usersCollection.insertOne(userDocument);
    }

    //
    @Override
    public void update(User user, String[] params) {
    }

    public User update(User user, String[] params, Object picture) {
        usersCollection.updateOne(
                Filters.eq("email", user.getEmail()),
                Updates.combine(
                        Updates.set("username", params[0]),
                        Updates.set("email", params[1]),
                        Updates.set("password", params[2]),
                        Updates.set("role", params[3]),
                        Updates.set("picture", picture)
                ));

        // Retrieve the updated user document from the database
        Document updatedUserDoc = (Document) usersCollection.find(Filters.eq("email", params[1])).first();
        // Create a new User object from the updated user document
        User updatedUser = new User(updatedUserDoc);
        return updatedUser;
    }


    @Override
    public void delete(User user) {
        //delete the user
        //document to delete
        Document userToDelete = new Document("username", user.getUsername())
                .append("email", user.getEmail())
                .append("role", user.getRole())
                .append("password", user.getPassword());

        usersCollection.deleteOne(userToDelete);
    }

    // retourne l'user dont l'email est donné en paramètre
    public User findByEmail(String email) {
        Document query = new Document("email", email);
        FindIterable<Document> docs = usersCollection.find(query);
        if (docs.first() == null) {
            return null;
        }
        return new User(docs.first());
    }

}