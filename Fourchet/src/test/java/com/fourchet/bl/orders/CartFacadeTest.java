package com.fourchet.bl.orders;

import com.fourchet.orders.Cart;
import com.fourchet.users.User;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class CartFacadeTest {


    @Test
    void getCart() throws Exception {
        CartFacade cartFacade = CartFacade.getInstance();
        User u = new User();
        u.setEmail("client@gmail.com");
        Cart cart = cartFacade.getCart(u);
        assertNotNull(cart);
    }
}