<<<<<<<< HEAD:Fourchet/src/main/java/com/fourchet/persist/UserDao.java
package com.fourchet.persist;


========
package com.fourchet.persist.account;


import com.fourchet.persist.Dao;
>>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a:Fourchet/src/main/java/com/fourchet/persist/account/UserDao.java
import com.fourchet.users.User;

import java.util.List;
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
}
