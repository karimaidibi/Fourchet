package com.fourchet.bl;

import com.fourchet.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserFacadeTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void register() {
    }

    @Test
    void login() {
        User clientUser = new User("client", "client@gmail.com", "client", "client");
        try {
            UserFacade facade = new UserFacade();
            User u = facade.login("client@gmail.com", "client");
            assertEquals(clientUser, u);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}