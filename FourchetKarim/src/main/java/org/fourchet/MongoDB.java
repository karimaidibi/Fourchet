package org.fourchet;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    //Create a MongoClientURI object
    private MongoClientURI uri;

    // Create a connection to the MongoDB server
    private MongoClient mongoClient;
    //database object
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
        // Create a MongoClientURI object
        this.uri = new MongoClientURI(
                "mongodb+srv://Fourchet:Fourchet@cluster0.3mq6o5k.mongodb.net/?retryWrites=true&w=majority");
        // Create a connection to the MongoDB server
        this.mongoClient = new MongoClient(uri);
        System.out.println("Connected to the database successfully");
        // Get the database
        this.database = this.mongoClient.getDatabase("fourchet");
        System.out.println("Connected to the database successfully");
    }

    public void closeConnection() {
        this.mongoClient.close();
    }

}
