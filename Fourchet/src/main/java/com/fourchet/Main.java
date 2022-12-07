package com.fourchet;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        // turn off the logger
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.OFF);

        //update the user
        //users.updateOne(userDocument, new Document("$set", new Document("username", "newUsername")));


        // Close the connection


    }
}