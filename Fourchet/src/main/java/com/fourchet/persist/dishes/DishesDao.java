package com.fourchet.persist.dishes;


import com.fourchet.dishes.Dish;
import com.fourchet.persist.Dao;
import com.fourchet.users.actitvities.Activity;

import java.util.List;
import java.util.Optional;

public abstract class DishesDao implements Dao<Dish> {


    @Override
    public Optional<Dish> get(long id) {
        return Optional.empty();
    }

    @Override
    public void save(Dish dish) {

    }

    @Override
    public void update(Dish dish, String[] params) {

    }

    @Override
    public void delete(Dish dish) {

    }

    public abstract Dish findByActivityAndName(String activity, String name);

    public abstract List<Dish> getAllByActivity(Activity activity);
}
