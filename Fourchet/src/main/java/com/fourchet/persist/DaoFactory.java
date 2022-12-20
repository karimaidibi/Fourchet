package com.fourchet.persist;

<<<<<<< HEAD
=======
import com.fourchet.persist.account.UserDao;
import com.fourchet.persist.account.UserDaoMongoDB;
>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a
import com.mongodb.client.MongoDatabase;

public class DaoFactory extends AbstractFactory {

    private UserDao userDao = null;

    // instanciate the database class
    private MongoDB mongoDB = new MongoDB();
<<<<<<< HEAD
    // get the db object
    private MongoDatabase mongoDatabase = mongoDB.getDBConnection();
=======

>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a
    public DaoFactory() {
        super();
    }

<<<<<<< HEAD
    public MongoDB getMongoDB() {
        return this.mongoDB;
    }

    public MongoDatabase getMongoDatabase() {
        return this.mongoDatabase;
=======
    public MongoDatabase getMongoDatabase() {
        return this.mongoDB.getDBConnection();
>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a
    }

    public UserDao getUserDao() {
        if (userDao==null) {
            this.userDao = new UserDaoMongoDB(this);
        }
        return userDao;
    }

<<<<<<< HEAD

=======
>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a
    //close the database connection
    public void closeConnectionToDB() {
        // Close the connection
        this.mongoDB.closeConnection();
    }

}
