package com.fourchet.persist.recipe;

import com.fourchet.persist.DaoFactory;
import com.fourchet.recipe.Recipe;
import com.fourchet.users.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeDaoMongoDB  extends RecipeDao {
    DaoFactory factory;

    // get the collection of recipes
    MongoCollection recipesCollection;

    // the connection to the database
    MongoDatabase mongoDatabase;

    public RecipeDaoMongoDB(DaoFactory factory) {
        this.factory = factory;
        this.mongoDatabase = factory.getMongoDatabase();
        this.recipesCollection = mongoDatabase.getCollection("recipes");
        System.out.println("Collection recipes selected successfully");
    }


    @Override
    public List<Recipe> getAll() {
        List recipes = new ArrayList<Recipe>();
        FindIterable<Document> documents = recipesCollection.find();
        System.out.println("Documents found successfully");
        int i = 0;
        for (Document doc : documents) {
            System.out.println(doc);
            recipes.add(new Recipe(doc));
            i++;
        }
        System.out.println(i);
        return recipes;
    }

    @Override
    public List<Recipe> getRecipeByFilter(String[]  filters) {
        List recipes = new ArrayList<Recipe>();

    Document DocumentFilter = new Document();
        for(int i=0;i<filters.length;i++){
            DocumentFilter.append(filters[i],filters[i+1]);
            i++;
        }

        Bson filter = Filters.and(
                new Document("$and", Arrays.asList(
                        DocumentFilter
                ))
        );

        FindIterable<Document> documents = recipesCollection.find(filter);
        System.out.println("Documents found successfully");

        for (Document doc : documents) {
            System.out.println(doc);
            recipes.add(new Recipe(doc));
        }
        return recipes;
    }


    @Override
    public void save(Recipe recipe) {
        Document document = new Document("Image", recipe.getImage())
                .append("Title", recipe.getTitle())
                .append("Description", recipe.getDescription())
                .append("Ingredients", recipe.getIngredients())
                .append("Steps", recipe.getSteps())
                .append("Author",recipe.getAuthor())
                .append("TypeOfRecipe",recipe.getType());
        this.recipesCollection.insertOne(document);
        System.out.println("Review succesfully added");
    }


    @Override
    public void updateRecipe(Recipe oldRecipe, Recipe newRecipe) {

        Bson filter = Filters.and(
                Filters.eq("Title", oldRecipe.getTitle()),
                Filters.eq("Author", oldRecipe.getAuthor()),
                Filters.eq("Description", oldRecipe.getDescription()),
                Filters.eq("Ingredients", oldRecipe.getIngredients()),
                Filters.eq("Steps", oldRecipe.getSteps())
        );

        Document newRecipeDocument = new Document("Title", newRecipe.getTitle())
                .append("Description", newRecipe.getDescription())
                .append("Ingredients", newRecipe.getIngredients())
                .append("Steps", newRecipe.getSteps())
                .append("Image",newRecipe.getImage())
                .append("TypeOfRecipe",newRecipe.getType());


        Document update = new Document("$set", newRecipeDocument);

        System.out.println("bon");
        recipesCollection.updateOne(filter, update);
    }

    @Override
    public void delete(Recipe recipe) {
        Document recipeDocument = new Document("Image", recipe.getImage())
                .append("Title", recipe.getTitle())
                .append("Description", recipe.getDescription())
                .append("Ingredients", recipe.getIngredients())
                .append("Steps", recipe.getSteps())
                .append("Author",recipe.getAuthor())
                .append("TypeOfRecipe",recipe.getType());
        recipesCollection.deleteOne(recipeDocument);
    }

    @Override
    public List<Recipe> findAllByAuthor(User user) {
        List recipes = new ArrayList<Recipe>();
        Document query = new Document("Author", user.getEmail());
        FindIterable<Document> documents = recipesCollection.find(query);

        for (Document doc : documents) {
            System.out.println(doc);
            recipes.add(new Recipe(doc));
        }
        return recipes;
    }


    public static void main(String[] args) throws Exception {
        /*

        DaoFactory factory = new DaoFactory();
        RecipeDaoMongoDB recipeDaoMongoDB = new RecipeDaoMongoDB(factory);
        //reviewDaoMongoDB.save(new Review(6, "Super", "zergzergzerg", "izergzerj"));
        //reviewDaoMongoDB.findOneReview("izergzerj", "hsfafh");
        //reviewDaoMongoDB.getAll();
        //
        Recipe R1 = recipeDaoMongoDB.findAllByAuthor(new User("usernametest", "email@gmail.com", "passwordtest","role")).get(0);
        //recipeDaoMongoDB.getAll();
        //System.out.println(R1.getImage());
        Recipe R2 = new Recipe("TitleNewNew","DescriptionNew","img",R1.getSteps(), R1.getIngredients(), R1.getAuthor());
        List<String> Ingredient3 = new ArrayList<String>();
        Ingredient3.add("Ingredient3");
        Ingredient3.add("Ingredient4");
        List<String> Steps3 = new ArrayList<String>();
        Steps3.add("Step1");
        Steps3.add("Step2");

        Recipe R3 = new Recipe("Ma recette","Ma descr","imgrecette",Steps3, Ingredient3, R1.getAuthor());
        /*
        Bson filter = Filters.eq("Title", R1.getTitle());
        Document D2 = new Document("Image", R2.getImage())
                .append("Title", R2.getTitle())
                .append("Description", R2.getDescription())
                .append("Ingredients", R2.getIngredients())
                .append("Steps", R2.getSteps());
        Document update = new Document("$set", D2);
        /*
        Document D1 = new Document("Title", R1.getTitle())
                .append("Description", R1.getDescription())
                .append("Ingredients", R1.getIngredients())
                .append("Steps", R1.getSteps())
                .append("Author",R1.getAuthor());
         */
        //recipeDaoMongoDB.update(R1,R2);
        //recipeDaoMongoDB.update(R1,R2);

        //String[] filters = {"Author","email@gmail.com"};
        //recipeDaoMongoDB.getRecipeByFilter(filters);
        //recipeDaoMongoDB.save(R3);

        //recipeDaoMongoDB.getAllRecipe();

    }
}
