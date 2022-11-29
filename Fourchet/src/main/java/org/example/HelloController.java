package com.example.jfxtest;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label errorMessage;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    protected void Login() throws IOException {
        if (email.getText().equals("") || password.getText().equals("")) {
            errorMessage.setText("Some fields are,missing !");
        }
        if (email.getText().equals("")) {
            email.setPromptText("email wrong !");
            email.setStyle("-fx-prompt-text-fill: red;");
        }
        if (password.getText().equals("")) {
            errorMessage.setText("Password null !");
            email.setPromptText("email wrong !");
            email.setStyle("-fx-prompt-text-fill: red;");
        }
        else {
            errorMessage.setText("Valide");
            Popup.display();
        }
    }
}