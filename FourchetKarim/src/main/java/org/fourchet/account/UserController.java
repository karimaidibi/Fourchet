package org.fourchet.account;

public class UserController {
    private UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    /**
     * Handles the login request from the user.
     * @param email
     * @param password
     * */
    public void login(String email, String password) throws Exception {
        try{
            userFacade.login(email, password);
        }
        catch (Exception e){
            // TODO : replace this by sending the message to the UI
            throw new Exception(e.getMessage());
        }


    }

    /**
     * Send message to user intefrace to display the message
     * */



}
