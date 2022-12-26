package com.fourchet.persist.account;


import com.fourchet.persist.Dao;
import com.fourchet.users.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class UserDao implements Dao<User> {


    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
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
}
