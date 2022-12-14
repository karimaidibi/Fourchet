package com.fourchet.persist;

import com.mongodb.client.MongoDatabase;

public class DaoFactory extends AbstractFactory {

    private UserDao userDao = null;

    // instanciate the database class
    private MongoDB mongoDB = new MongoDB();
    // get the db object
    private MongoDatabase mongoDatabase = mongoDB.getDBConnection();
    public DaoFactory() {
        super();
    }

    public MongoDB getMongoDB() {
        return this.mongoDB;
    }

    public MongoDatabase getMongoDatabase() {
        return this.mongoDatabase;
    }

    public UserDao getUserDao() {
        if (userDao==null) {
            this.userDao = new UserDaoMongoDB(this);
        }
        return userDao;
    }


    //close the database connection
    public void closeConnectionToDB() {
        // Close the connection
        this.mongoDB.closeConnection();
    }

}
