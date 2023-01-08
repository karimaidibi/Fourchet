package com.fourchet.recipe;

import org.bson.Document;

import java.util.List;

public class Recipe {

    String title;

    String description;

    String Image;

    List<String> steps;

    List<String> ingredients;

    String Author;

    TypeOfRecipe Type;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return Image;
    }

    public List<String> getSteps() {
        return steps;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getAuthor() {
        return Author;
    }

    public String getType() {
        return Type.toString();
    }

    public Recipe(String title, String description, String Image, List<String> steps, List<String>ingredients, String Author, TypeOfRecipe Type) {
        this.title = title;
        this.description = description;
        this.Image = Image;
        this.steps = steps;
        this.ingredients = ingredients;
        this.Author = Author;
        this.Type = Type;
    }

    public Recipe (Document document) {
        this.title = (String) document.get("Title");
        this.description = (String) document.get("Description");
        this.Image = (String) document.get("Image");
        this.steps = (List<String>) document.get("Steps");
        this.ingredients = (List<String>) document.get("Ingredients");
        this.Author = (String) document.get("Author");
        this.Type = TypeOfRecipe.getType((String)document.get("TypeOfRecipe"));
    }
}
