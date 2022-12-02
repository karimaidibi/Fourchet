package org.fourchet;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        // turn off the logger
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.OFF);

        // instanciate the database class
        Database db = Database.getInstance();
        // get the db object
        MongoDatabase fourchetDB = db.getDB();

        // get the collection
        MongoCollection users = fourchetDB.getCollection("users");
        System.out.println("Collection users selected successfully");

        // A request to Find all documents in the collection of users
        FindIterable documents = users.find();

        // Iterate through the documents
        for (Object doc : documents) {
            System.out.println(doc);
        }

        // create a new user
        // Create the document
        Document userDocument = new Document("username", "provider")
                .append("email", "provider@gmail.com")
                .append("role", "provider")
                .append("password", "provider");

        // insert a user into the collection of users
        users.insertOne(userDocument);

        //update the user
        users.updateOne(userDocument, new Document("$set", new Document("username", "newUsername")));

        //delete the user
        //document to delete
        Document userToDelete = new Document("username", "newUsername")
                .append("email", "provider@gmail.com")
                .append("role", "provider")
                .append("password", "provider");

        users.deleteOne(userToDelete);

        // Close the connection
        db.getMongoClient().close();


    }
}