package com.fourchet.account.persist;

import com.fourchet.MongoDB;
import com.fourchet.MongoDBFactory;
import com.fourchet.account.users.User;
import com.fourchet.account.UserDao;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDaoMongoDB extends UserDao {

    // MongoDB factory
    private MongoDBFactory mongoDBFactory = MongoDBFactory.getInstance();

    // instanciate the database class
    MongoDB mongoDB = mongoDBFactory.getMongoDB();
    // get the db object
    MongoDatabase mongoDatabase = mongoDB.getDBConnection();

    // get the collection of users
    MongoCollection usersCollection;

    public UserDaoMongoDB() {
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
                .append("password", user.getPassword());

        // insert a user into the collection of users
        usersCollection.insertOne(userDocument);
    }

    //
    @Override
    public void update(User user, String[] params) {
        user.setEmail(Objects.requireNonNull(
                params[0], "Email cannot be null"));
        user.setPassword(Objects.requireNonNull(
                params[1], "Password cannot be null"));
        user.setRole(Objects.requireNonNull(
                params[2], "Role cannot be null"));
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

    //close the database connection
    public void closeConnectionToDB() {
        // Close the connection
        this.mongoDB.closeConnection();
    }
}