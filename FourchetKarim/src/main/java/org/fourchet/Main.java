package org.fourchet;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.fourchet.account.UserDao;
import org.fourchet.account.UserDaoMongoDB;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        // turn off the logger
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.OFF);

        UserDaoMongoDB dao = new UserDaoMongoDB();
        dao.getAll();

        System.out.println(dao.findByEmail("client@gmail.com").getUsername());


        //update the user
        //users.updateOne(userDocument, new Document("$set", new Document("username", "newUsername")));


        // Close the connection


    }
}