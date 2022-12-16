package com.fourchet.UI;

import com.fourchet.bl.UserFacade;
import com.fourchet.users.User;

public class UserController {
    private UserFacade userFacade;

    public UserController() {
        this.userFacade = new UserFacade();
    }

    /**
     * Handles the login request from the user.
     * @param email
     * @param password
     * */
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

    public User register(String username, String email, String password, String role) throws Exception {
        try{
            User user = userFacade.register(new User(username, email, password, role));
            return user;
        }
        catch (Exception e){
            // TODO : replace this by sending the message to the UI
            throw new Exception(e.getMessage());
        }
    }

}
