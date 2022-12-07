package com.fourchet.account;

// This class below is a singleton class
public class UserDaoFactory {
    private static UserDaoFactory instance = null;
    private UserDaoMongoDB userDaoMongoDB;

    private UserDaoFactory() {
        this.userDaoMongoDB = new UserDaoMongoDB();
    }

    public static UserDaoFactory getInstance() {
        if (instance == null) {
            instance = new UserDaoFactory();
        }
        return instance;
    }

    public UserDaoMongoDB getUserDao() {
        return this.userDaoMongoDB;
    }

}
