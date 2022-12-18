package com.fourchet.account.bl;

import com.fourchet.account.users.User;
import com.fourchet.account.UserDaoFactory;
import com.fourchet.account.persist.UserDaoMongoDB;

public class UserFacade {

    // The UserDaoFactory
    private UserDaoFactory userDaoFactory;

    // The UserDao
    private UserDaoMongoDB userDaoMongoDB;

    // The current user
    private User currentUser;

    public UserFacade() {
        // Get the instance of the UserDaoFactory
        this.userDaoFactory = UserDaoFactory.getInstance();
        // Get the instance of the UserDao
        this.userDaoMongoDB = userDaoFactory.getUserDao();
    }

    // delegate the user dao to save the user
    public void saveUser(User user)
    {
        try {
            userDaoMongoDB.save(user);
            this.currentUser = user;
        }catch (Exception e){
            // TODO : replace this by sending the message to the UI
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used to handle the login of the user
     *
     * @param email
     * @param password
     * @return the user if the email and password are correct
     * @throws Exception if the email or password are incorrect
     */
    public User login(String email, String password) throws Exception {
        User user = userDaoMongoDB.findByEmail(email);
        if (user == null) {
            throw new Exception("Email not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new Exception("Password incorrect");
        }
        this.currentUser = user;
        return user;

    }
}
