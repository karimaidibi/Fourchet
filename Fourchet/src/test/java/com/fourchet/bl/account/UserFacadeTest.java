package com.fourchet.bl.account;

import com.fourchet.users.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserFacadeTest {

    /**
     * this test doesn't work because the created client object has a different id than
     * the one that is returned from the database and wrapped in the User object
     */
    @Test
    void login() {
        User clientUser = new User("client", "client@gmail.com", "client", "client");
        try {
            UserFacade facade = UserFacade.getInstance();
            User u = facade.login("client@gmail.com", "client");
            assertEquals(clientUser, u);
            // TODO : replace the code above by this one may be a solution
            //assertTrue(u.getUsername().equals("client") && u.getEmail().equals("client@gmail.com"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}