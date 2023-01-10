package com.fourchet.products;

import com.fourchet.users.actitvities.Activity;
import org.bson.Document;

import java.text.NumberFormat;
import java.text.ParseException;

public class Product {
    private String ownerEmail;
    private String ownerActivityName;
    private String name;
    private ProductCategory category;
    private double price;

    public Product(String ownerEmail, String ownerActivityName, String name, ProductCategory category, double price) {
        this.ownerEmail = ownerEmail;
        this.ownerActivityName = ownerActivityName;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product(Document document) throws ParseException {
        this.ownerEmail = (String) document.get("ownerEmail");
        this.ownerActivityName = (String) document.get("ownerActivityName");
        this.name = (String) document.get("name");
        this.category = new ProductCategory((String) document.get("category"));
        NumberFormat nf = NumberFormat.getInstance();
        Object price = document.get("price");
        String str = String.valueOf(price);
        Number n = nf.parse(str);
        this.price =  n.doubleValue();

    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerActivityName() {
        return ownerActivityName;
    }

    public void setOwnerActivityName(String ownerActivityName) {
        this.ownerActivityName = ownerActivityName;
    }

    public Document getDocument() {
        Document document = new Document();
        document.append("ownerEmail", ownerEmail);
        document.append("ownerActivityName", ownerActivityName);
        document.append("name", name);
        document.append("category", category.getName());
        document.append("price", price);
        return document;
    }
}
