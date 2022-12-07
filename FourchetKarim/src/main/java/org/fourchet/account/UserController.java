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
