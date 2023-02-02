package com.fourchet.products;

import org.bson.Document;

public class ProductCategory {
    private String name;

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory(Document document) {
        this.name = (String) document.get("name");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
