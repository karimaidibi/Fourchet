package com.fourchet.bl.account;

import com.fourchet.users.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserFacadeTest {

    /**
     * this test doesn't work because the created client object has a different id than
     * the one that is returned from the database and wrapped in the User object
     */
    @Test
    void login() {
        try {
            UserFacade facade = UserFacade.getInstance();
            User u = facade.login("admin@gmail.com", "admin");
            assertTrue(u.getUsername().equals("admin") && u.getEmail().equals("admin@gmail.com"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}