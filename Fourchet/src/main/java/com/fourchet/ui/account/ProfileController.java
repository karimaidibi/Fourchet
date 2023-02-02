package com.fourchet.ui.account;

import com.fourchet.bl.account.UserFacade;
import com.fourchet.users.User;

public class ProfileController {

    private UserFacade userFacade;

    public ProfileController() {
        this.userFacade = UserFacade.getInstance();
    }

    // get the user from the facade
    public User getUser(){
        return userFacade.getCurrentUser();
    }

    // delegate the user facade to update the user
    public void updateUserInfos(User user, String[] params, Object picture) throws Exception {
        try {
            userFacade.update(user, params, picture);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



}
