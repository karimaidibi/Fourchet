package com.fourchet.ui.account.activities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static Stage generalStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ActivitiesFrame.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Activities Frame");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        this.generalStage = stage;

    }

    public Stage getGeneralStage() {
        return generalStage;
    }



    public static void main(String[] args) {
        launch();
    }
}