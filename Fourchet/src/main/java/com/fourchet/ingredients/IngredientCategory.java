package com.fourchet.ingredients;

import org.bson.Document;

public class IngredientCategory {
    private String name;

    public IngredientCategory(String name) {
        this.name = name;
    }

    public IngredientCategory(Document document) {
        this.name = (String) document.get("name");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
