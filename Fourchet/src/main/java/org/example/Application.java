package com.example.jfxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Popup;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Signin.fxml"));
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