package com.fourchet.ingredients;

public class Ingredient {
    private String name;
    private IngredientCategory category;

    public Ingredient(String name, IngredientCategory category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public IngredientCategory getCategory() {
        return category;
    }
}
