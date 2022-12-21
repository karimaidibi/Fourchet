package com.fourchet.ui.account;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoaderLogin = new FXMLLoader(Application.class.getResource("Login.fxml"));
        Parent root = fxmlLoaderLogin.load();
        LoginFrame loginController = fxmlLoaderLogin.getController();
        // Récupération de l'objet Login associé au fichier FXML
        Scene scene = new Scene(root, 320, 240);

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
}