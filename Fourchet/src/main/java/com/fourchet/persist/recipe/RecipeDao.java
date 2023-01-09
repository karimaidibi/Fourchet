package com.fourchet.persist.recipe;

import com.fourchet.persist.Dao;
import com.fourchet.recipe.Recipe;
import com.fourchet.users.User;

import java.util.List;
import java.util.Optional;

public class RecipeDao implements Dao<Recipe> {
    @Override
    public Optional<Recipe> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Recipe> getAll() {
        return null;
    }

    @Override
    public void save(Recipe recipe) {

    }

    @Override
    public void update(Recipe recipe, String[] params) {

    }

    public void updateRecipe(Recipe oldrecipe, Recipe newrecipe) {

    }

    @Override
    public void delete(Recipe recipe) {
    }

    public List<Recipe> getRecipeByFilter(String[] filter) {
        return null;
    }

    public List<Recipe> findAllByAuthor(User user) {
        return null;
    }
}
