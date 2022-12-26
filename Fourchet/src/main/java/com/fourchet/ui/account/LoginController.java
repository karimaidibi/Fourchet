package com.fourchet.ui.account;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.users.User;

public class LoginController {
    private UserFacade userFacade;

    public LoginController() {
        this.userFacade = UserFacade.getInstance();
    }

    /**
     * Handles the login request from the user.
     * @param email
     * @param password
     * @return the user if the email and password are correct
     * @throws Exception if the email or password are incorrect
     */
    public User login(String email, String password) throws Exception {
        try{
            User user = userFacade.login(email, password);
            return user;
        }
        catch (Exception e){
            // TODO : replace this by sending the message to the UI
            throw new Exception(e.getMessage());
        }
    }
}
