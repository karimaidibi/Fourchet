package org.fourchet.account;

public class UserFacade {

    // The user facade knows the UserDao
    private UserDao userDao;

    public UserFacade(UserDao userDAO) {
        this.userDao = userDAO;
    }

    // delegate the user dao to save the user
    public void saveUser(User user) {
        userDao.save(user);
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
        return user;

    }
}
