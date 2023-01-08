package com.fourchet.persist;

import com.fourchet.persist.account.UserDao;
import com.fourchet.persist.account.UserDaoMongoDB;
import com.fourchet.persist.recipe.RecipeDaoMongoDB;
import com.fourchet.persist.review.ReviewDaoMongoDB;
import com.mongodb.client.MongoDatabase;

public class DaoFactory extends AbstractFactory {

    private UserDao userDao = null;

    private ReviewDaoMongoDB reviewDaoMongoDB = null;

    private RecipeDaoMongoDB recipeDaoMongoDB = null;

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

    public RecipeDaoMongoDB getRecipeDaoMongoDB() {
        if (recipeDaoMongoDB==null) {
            this.recipeDaoMongoDB = new RecipeDaoMongoDB(this);
        }
        return recipeDaoMongoDB;
    }

    //close the database connection
    public void closeConnectionToDB() {
        // Close the connection
        this.mongoDB.closeConnection();
    }

}
