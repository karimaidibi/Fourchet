package com.fourchet.ui.account;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.users.User;

public class RegisterController {

    private UserFacade userFacade;

    public RegisterController() {
        this.userFacade = UserFacade.getInstance();
    }

    /**
     * Handles the register request from the user.
     *
     * @param username
     * @param email
     * @param password
     * @param role
     * @return the user if the email and password are correct
     * @throws Exception if the email or password are incorrect
     */
    public User register(String username, String email, String password, String role) throws Exception {
        try {
            User user = userFacade.register(new User(username, email, password, role));
            return user;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
