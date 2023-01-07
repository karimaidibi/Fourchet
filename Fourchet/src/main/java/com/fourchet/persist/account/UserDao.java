package com.fourchet.persist.account;


import com.fourchet.orders.Cart;
import com.fourchet.persist.Dao;
import com.fourchet.users.User;
import org.bson.Document;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class UserDao implements Dao<User> {


    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }


    @Override
    public void save(User user) {

    }
    @Override
    public void update(User user, String[] params) {
    }

    @Override
    public void delete(User user) {

    }

    public abstract User findByEmail(String email);

    public User update(User user, String[] params, Object picture) {
        return null;
    };

    public Cart getCart(User user) {
        return null;
    }

    public void updateCart(User user, Cart cart) {}

    public void updateDeliveryAddress(User user, String address) {}
}
