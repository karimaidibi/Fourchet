package com.fourchet.persist.ingredients;


import com.fourchet.ingredients.Ingredient;
import com.fourchet.persist.Dao;

import java.util.Optional;

public abstract class IngredientsDao implements Dao<Ingredient> {


    @Override
    public Optional<Ingredient> get(long id) {
        return Optional.empty();
    }

    @Override
    public void save(Ingredient ingredient) {

    }

    @Override
    public void update(Ingredient ingredient, String[] params) {

    }

    public abstract Ingredient findByName(String name);
}
