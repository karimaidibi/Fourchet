<<<<<<<< HEAD:Fourchet/src/main/java/com/fourchet/UI/UserController.java
package com.fourchet.UI;

import com.fourchet.bl.UserFacade;
========
package com.fourchet.ui.account;

import com.fourchet.bl.account.UserFacade;
>>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a:Fourchet/src/main/java/com/fourchet/ui/account/LoginController.java
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
