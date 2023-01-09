package com.fourchet.ui.account;

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
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login or Sign-Up Form!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void goToNextScene(ActionEvent event, String fxmlFrameName) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxmlFrameName));
            Parent fxmlRoot = loader.load();

            // Create a new scene with the destination scene as the root node
            Scene scene = new Scene(fxmlRoot);

            // Get the stage from the event source (e.g., a button)
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.hide();
            // Set the scene for the stage
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}