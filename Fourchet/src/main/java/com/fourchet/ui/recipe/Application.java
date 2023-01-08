package com.fourchet.ui.recipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        /*
        FXMLLoader fxmlLoaderLogin = new FXMLLoader(Application.class.getResource("Login.fxml"));
        Parent root = fxmlLoaderLogin.load();
        LoginFrame loginController = fxmlLoaderLogin.getController();
        Scene scene = new Scene(root, 320, 240);

         */

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com.fourchet.ui.recipe/RecipeSearchFrame.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 320, 240);
        /*
        FXMLLoader fxmlLoaderRecipe = new FXMLLoader(Application.class.getResource("RecipeCreateFrame.fxml"));
        Parent root = fxmlLoaderRecipe.load();
        RecipeController RecipeController = fxmlLoaderRecipe.getController();
        Scene scene = new Scene(root, 320, 240);

         */



        // Accès à l'attribut privé gridpane de la classe Login
        //GridPane gridpane = loginController.getGridpaneLogin();

        //FXMLLoader fxmlLoaderProfile = new FXMLLoader(Application.class.getResource("ProfileFrame.fxml"));
        //ProfileFrame profileController = fxmlLoaderLogin.getController();
        //GridPane gridpaneProfile = profileController.getGridpaneProfile();

        stage.setTitle("Login or Sign-Up Form!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void goToNextScene(ActionEvent event, String fxmlFrameName) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxmlFrameName));
            Parent fxmlRoot = loader.load();

            // Create a new scene with the destination scene as the root node
            Scene scene = new Scene(fxmlRoot);

            // Get the stage from the event source (e.g., a button)
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            // Set the scene for the stage
            stage.setScene(scene);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

