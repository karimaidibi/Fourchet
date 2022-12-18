package com.fourchet;

//import all the needed classes for mongodb

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    //the link to connect to the database
    private String uri;

    // The connection to mongodb
    private MongoClient mongoClient;

    // The connection to the database
    private MongoDatabase database;

    //get mongo client
    public MongoClient getMongoClient() {
        return this.mongoClient;
    }

    // get the database
    public MongoDatabase getDBConnection() {
        return this.database;
    }

    public MongoDB() {
        // turn off the logger
        //Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        //mongoLogger.setLevel(Level.OFF);

        // Create a MongoClientURI object
        this.uri = "mongodb+srv://Fourchet:Fourchet@cluster0.3mq6o5k.mongodb.net/?retryWrites=true&w=majority";
        // Create a connection to the MongoDB server
        try {
            this.mongoClient = MongoClients.create(uri);
            System.out.println("Connected to the database successfully");

            // Get the database
            this.database = this.mongoClient.getDatabase("fourchet");
            System.out.println("Connected to the database successfully");

        } catch (Exception e) {
            System.out.println("Error while connecting to the database");
            System.out.println(e);
        }

    }

    public void closeConnection() {
        this.mongoClient.close();
    }

}
