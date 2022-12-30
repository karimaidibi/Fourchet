package com.fourchet.ui.ingredients;

import com.fourchet.ingredients.Ingredient;
import com.fourchet.ingredients.IngredientCategory;
import com.fourchet.persist.DaoFactory;
import com.fourchet.persist.ingredientCategories.IngredientCategoriesDao;
import com.fourchet.persist.ingredientCategories.IngredientCategoriesDaoMongoDB;
import com.fourchet.persist.ingredients.IngredientsDaoMongoDB;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("IngredientsCategoryManagement.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login or Sign-Up Form!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}