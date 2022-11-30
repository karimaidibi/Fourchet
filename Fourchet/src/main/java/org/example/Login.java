package com.example.jfxtest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Login {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    protected void Login() throws IOException {
        if (email.getText().equals("") || password.getText().equals("")) {
        }
        if (email.getText().equals("")) {
            email.setPromptText("email wrong !");
            email.setStyle("-fx-prompt-text-fill: red;");
        }
        if (password.getText().equals("")) {
            email.setPromptText("email wrong !");
            email.setStyle("-fx-prompt-text-fill: red;");
        }
        else {


        }
    }
}