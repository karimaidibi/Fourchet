package com.fourchet;

// THis class below is a singleton class
public class MongoDBFactory {
    private static MongoDBFactory instance = null;
    private MongoDB mongoDB;

    private MongoDBFactory() {
        this.mongoDB = new MongoDB();
    }

    public static MongoDBFactory getInstance() {
        if (instance == null) {
            instance = new MongoDBFactory();
        }
        return instance;
    }

    public MongoDB getMongoDB() {
        return this.mongoDB;
    }

}
