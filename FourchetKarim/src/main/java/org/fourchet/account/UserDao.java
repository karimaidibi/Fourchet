package org.fourchet.account;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.fourchet.Dao;
import org.fourchet.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDao implements Dao<User> {

    // TODO : remove this list and use the database instead or use the database as a cache
    private List<User> users = new ArrayList<>();

    // instanciate the database class
    Database db = Database.getInstance();
    // get the db object
    MongoDatabase fourchetDB = db.getDB();

    // get the collection of users
    MongoCollection usersCollection;

    public UserDao() {
        this.usersCollection = fourchetDB.getCollection("users");
        System.out.println("Collection users selected successfully");
    }

    // retourne les users qui sont provider dans la base
    public List<User> getProviders() {
        List<User> providers = new ArrayList<>();
        for (User u : users) {
            if (u.getRole().equals("p")) {
                providers.add(u);
            }
        }
        return providers;
    }

    // retourne les users qui sont client dans la base
    public List<User> getClients() {
        List<User> clients = new ArrayList<>();
        for (User u : users) {
            if (u.getRole().equals("c")) {
                clients.add(u);
            }
        }
        return clients;
    }


    // retourne l'user dont l'id est donné en paramètre
    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get((int) id));
    }

    /**
     * return all the users in the database as a list of users objects
     * it should replace the FindIterable<Document> by List<User> or add the founded users to the list of users
     */
    @Override
    public List<User> getAll() {
        // A request to Find all documents in the collection of users
        FindIterable documents = usersCollection.find();
        // Iterate through the documents
        for (Object doc : documents) {
            System.out.println(doc);
        }
        return users;
    }

    @Override
    public void save(User user) {
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
        users.add(user);
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
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    //close the database connection
    public void closeConnectionToDB() {
        // Close the connection
        db.getMongoClient().close();
    }
}