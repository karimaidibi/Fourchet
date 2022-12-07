package com.fourchet;

import com.fourchet.account.UserDaoMongoDB;
import com.fourchet.account.UserFacade;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws Exception {

        // turn off the logger
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.OFF);

        UserFacade facade = new UserFacade();
        //System.out.println(facade.login("client@gmail.com", "client").getUsername());
        facade.getAll();

        //update the user
        //users.updateOne(userDocument, new Document("$set", new Document("username", "newUsername")));


        // Close the connection


    }
}