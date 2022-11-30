package com.example.jfxtest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Popup {



    public static void display() throws IOException {
        Stage popupWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Popup.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        popupWindow.setScene(scene);
        popupWindow.show();
    }

}
