package com.fourchet.ui.account;

import com.fourchet.bl.account.UserFacade;

public class ProfileController {


    private UserFacade userFacade;


    public ProfileController() {
        this.userFacade = UserFacade.getInstance();
    }
}
