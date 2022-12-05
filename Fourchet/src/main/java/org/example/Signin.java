package com.example.jfxtest;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Signin {
    @FXML
    private BorderPane GeneralPane;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;

    @FXML
    private ChoiceBox Role;

    @FXML
    protected void Signin() throws IOException {


        if (email.getText().equals("") || password.getText().equals("")||confirmPassword.getText().equals("")) {
            if (email.getText().equals("")) {
                email.setText("");
                email.setPromptText("email missing !");
                email.setStyle("-fx-prompt-text-fill: red;-fx-border-color: red;");
                new animatefx.animation.Shake(email).play();
            }
            else {
                email.setStyle("-fx-prompt-text-fill: black;-fx-border-color: none;");
            }
            if (password.getText().equals("")) {
                password.setText("");
                password.setPromptText("password missing !");
                password.setStyle("-fx-prompt-text-fill: red;-fx-border-color: red;");
                new animatefx.animation.Shake(password).play();
            }
            else {
                password.setStyle("-fx-prompt-text-fill: black;-fx-border-color: none;");
            }
            if(confirmPassword.getText().equals("")){
                confirmPassword.setText("");
                confirmPassword.setPromptText("confirm password missing !");
                confirmPassword.setStyle("-fx-prompt-text-fill: red;-fx-border-color: red;");
                new animatefx.animation.Shake(confirmPassword).play();
            }
            else {
                confirmPassword.setStyle("-fx-prompt-text-fill: black;-fx-border-color: none;");
            }

        } else if (!password.getText().equals(confirmPassword.getText())) {
            email.setStyle("-fx-prompt-text-fill: black;-fx-border-color: none;");
            password.setText("");
            confirmPassword.setText("");
            password.setPromptText("passwords don't match !");
            password.setStyle("-fx-prompt-text-fill: red;-fx-border-color: red;");
            confirmPassword.setPromptText("passwords don't match !");
            confirmPassword.setStyle("-fx-prompt-text-fill: red;-fx-border-color: red;");
            new animatefx.animation.Shake(password).play();
            new animatefx.animation.Shake(confirmPassword).play();
        }
        else {
            Popup popup = new Popup();
            String select = (String) Role.getSelectionModel().getSelectedItem();
            popup.showAlert(Alert.AlertType.ERROR, GeneralPane.getScene().getWindow(), "Connection Failed", "Role : " + select);
        }
    }
}
