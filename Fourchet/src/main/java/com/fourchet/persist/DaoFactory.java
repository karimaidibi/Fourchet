package com.fourchet.persist;

import com.fourchet.persist.account.UserDao;
import com.fourchet.persist.account.UserDaoMongoDB;
import com.fourchet.persist.review.ReviewDaoMongoDB;
import com.mongodb.client.MongoDatabase;

public class DaoFactory extends AbstractFactory {

    private UserDao userDao = null;

    private ReviewDaoMongoDB reviewDaoMongoDB = null;

    // instanciate the database class
    private MongoDB mongoDB = new MongoDB();

    public DaoFactory() {
        super();
    }

    public MongoDatabase getMongoDatabase() {
        return this.mongoDB.getDBConnection();
    }

    public UserDao getUserDao() {
        if (userDao==null) {
            this.userDao = new UserDaoMongoDB(this);
        }
        return userDao;
    }

    public ReviewDaoMongoDB getReviewDaoMongoDB() {
        if (reviewDaoMongoDB==null) {
            this.reviewDaoMongoDB = new ReviewDaoMongoDB(this);
        }
        return reviewDaoMongoDB;
    }

    //close the database connection
    public void closeConnectionToDB() {
        // Close the connection
        this.mongoDB.closeConnection();
    }

}
