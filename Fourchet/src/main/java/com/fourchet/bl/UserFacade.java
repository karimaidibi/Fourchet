package com.fourchet.bl;

import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.UserDao;
import com.fourchet.users.User;

public class UserFacade {

    // The UserDaoFactory
    private AbstractFactory abstractFactory;
    // The UserDao
    private UserDao userDao;

    private User currentUser;

    public UserFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.userDao = abstractFactory.getUserDao();
    }

    // delegate the user dao to save the user
    public void saveUser(User user)
    {
        try {
            userDao.save(user);
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
