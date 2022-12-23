package com.fourchet.ingredients;

import org.bson.Document;

public class Ingredient {
    private String name;
    private IngredientCategory category;

    public Ingredient(String name, IngredientCategory category) {
        this.name = name;
        this.category = category;
    }

    public Ingredient(Document document) {
        this.name = (String) document.get("name");
        this.category = new IngredientCategory((String) document.get("category"));
    }

    public String getName() {
        return name;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(IngredientCategory category) {
        this.category = category;
    }
}
