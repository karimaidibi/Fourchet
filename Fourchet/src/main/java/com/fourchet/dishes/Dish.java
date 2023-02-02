package com.fourchet.dishes;

import com.fourchet.dishes.typeCuisine.TypeOfCuisine;
import com.fourchet.users.actitvities.Activity;
import org.bson.Document;

import java.util.List;

public class Dish {
    private String activityOwner;

    private String emailOwner;

    private String title;

    private String description;

    private String image;

    private List<String> ingredients;

    private String category;

    private TypeOfCuisine typeOfCuisine;

    private double price;

    public Dish(String activityOwner, String title, String description, String image, List<String> ingredients, String category, TypeOfCuisine typeOfCuisine, double price,String emailOwner) {
        this.activityOwner = activityOwner;
        this.title = title;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
        this.category = category;
        this.typeOfCuisine = typeOfCuisine;
        this.price = price;
        this.emailOwner = emailOwner;
    }

    public Dish(Document document) {
        this.activityOwner = (String) document.get("activityOwner");
        this.title = (String) document.get("title");
        this.description = (String) document.get("description");
        this.image = (String) document.get("image");
        this.ingredients = (List<String>) document.get("ingredients");
        this.category = (String) document.get("category");
        this.typeOfCuisine = (TypeOfCuisine) document.get("typeOfCuisine");
        this.price = (double) document.get("price");
        this.emailOwner = (String) document.get("emailOwner");
    }

    public String getActivityOwner() {
        return activityOwner;
    }

    public void setActivityOwner(String activityOwner) {
        this.activityOwner = activityOwner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TypeOfCuisine getTypeOfCuisine() {
        return typeOfCuisine;
    }

    public void setTypeOfCuisine(TypeOfCuisine typeOfCuisine) {
        this.typeOfCuisine = typeOfCuisine;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEmailOwner() {
        return emailOwner;
    }

    public void setEmailOwner(String emailOwner) {
        this.emailOwner = emailOwner;
    }
}