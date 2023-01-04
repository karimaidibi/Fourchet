package com.fourchet.persist.ingredientCategories;


import com.fourchet.ingredients.IngredientCategory;
import com.fourchet.persist.Dao;

import java.util.Optional;

public abstract class IngredientCategoriesDao implements Dao<IngredientCategory> {


    @Override
    public Optional<IngredientCategory> get(long id) {
        return Optional.empty();
    }

    @Override
    public void save(IngredientCategory ingredientCategory) {

    }

    @Override
    public void update(IngredientCategory ingredientCategory, String[] params) {

    }

    @Override
    public void delete(IngredientCategory ingredientCategory) {

    }

    public abstract IngredientCategory findByName(String name);
}
