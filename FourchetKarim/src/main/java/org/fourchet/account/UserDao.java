package org.fourchet.account;

import org.fourchet.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDao implements Dao<User> {

    private List<User> users = new ArrayList<>();

    public UserDao() {
        users.add(new User("john@domain.com", "psw1", "c"));
        users.add(new User("susan@domain.com", "psd2", "p"));
    }

    // retourne les users qui sont provider dans la base
    public List<User> getProviders() {
        List<User> providers = new ArrayList<>();
        for (User u : users) {
            if (u.getRole().equals("p")) {
                providers.add(u);
            }
        }
        return providers;
    }

    // retourne les users qui sont client dans la base
    public List<User> getClients() {
        List<User> clients = new ArrayList<>();
        for (User u : users) {
            if (u.getRole().equals("c")) {
                clients.add(u);
            }
        }
        return clients;
    }

    // retourne l'user dont l'id est donné en paramètre
    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get((int) id));
    }

    // retourne tous les users de la base
    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(User user, String[] params) {
        user.setEmail(Objects.requireNonNull(
                params[0], "Email cannot be null"));
        user.setPassword(Objects.requireNonNull(
                params[1], "Password cannot be null"));
        user.setRole(Objects.requireNonNull(
                params[2], "Role cannot be null"));
        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}