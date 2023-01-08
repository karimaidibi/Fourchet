package com.fourchet.persist.account.activities;


import com.fourchet.persist.Dao;
import com.fourchet.users.User;
import com.fourchet.users.actitvities.Activity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class ActivitiesDao implements Dao<Activity> {


    @Override
    public Optional<Activity> get(long id) {
        return Optional.empty();
    }


    @Override
    public void save(Activity activity) {

    }
    @Override
    public void update(Activity activity, String[] params) {
    }

    @Override
    public void delete(Activity activity) {
    }

    public List<Activity> getAllByUser(User user) {return null;};

    public abstract Activity findByName(User user, String name);

    public Activity update(Activity activity, String[] params, Object picture) {
        return null;
    };
}
