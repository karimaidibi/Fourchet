package com.fourchet.bl.account;

import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.account.UserDao;
import com.fourchet.users.User;

// This class below is a singleton class (we create the facade only once)
public class UserFacade {
    private static UserFacade instance = null;

    // The UserDaoFactory
    private AbstractFactory abstractFactory;
    // The UserDao
    private UserDao userDao;

    private User currentUser;

    private UserFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.userDao = abstractFactory.getUserDao();
    }

    public static UserFacade getInstance() {
        if (instance == null) {
            instance = new UserFacade();
        }
        return instance;
    }

    // delegate the user dao to save the user
    public User register(User user) throws Exception
    {
        try {
            User existingUser = userDao.findByEmail(user.getEmail());
            if (existingUser == null) {
                userDao.save(user);
                this.currentUser = user;
            }
            else {
                System.out.println("Email already registered !");
                return null;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Error during the connection to the database");
        }
        return user;
    }

    public User Modify(User user)
    {
        String[] params = {user.getUsername(),user.getPassword()};
        userDao.update(user,params);
        return user;
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
        User user = userDao.findByEmail(email);
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
