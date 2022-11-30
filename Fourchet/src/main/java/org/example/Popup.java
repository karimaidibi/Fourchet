package com.example.jfxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Popup {



    public static void display() throws IOException {
        Stage popupWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Popup.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        popupWindow.setScene(scene);
        popupWindow.show();
    }

}
