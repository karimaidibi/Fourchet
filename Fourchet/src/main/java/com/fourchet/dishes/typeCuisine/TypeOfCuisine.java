package com.fourchet.dishes.typeCuisine;

import org.bson.Document;

public class TypeOfCuisine {
    private String name;

    public TypeOfCuisine(String name) {
        this.name = name;
    }

    public TypeOfCuisine(Document document) {
        this.name = (String) document.get("name");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
